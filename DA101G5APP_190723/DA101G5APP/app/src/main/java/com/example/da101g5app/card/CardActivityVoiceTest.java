package com.example.da101g5app.card;

import android.animation.Animator;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CardActivityVoiceTest extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {
    private TextView activity_card_voice_test_ans_area;
    private Button act_card_voice_test_speaker;
    private Button act_card_voice_test_choice_voiceBtn;
    private TextView act_card_voice_test_word;
    private TextView act_card_voice_test_pron;
    private TextView act_card_voice_test_said;



    private String member_card_group_id;
    private List<CardVOApp> cardVOAppList;
    private List<CardVOApp> tempList = new ArrayList<CardVOApp>();
    private ArrayList<Button> rndChoiceButtons;

    private TextView act_card_voice_test_total_left;
    private TextView act_card_voice_test_total_right;
    private TextView act_card_voice_test_correctNum;
    private TextView act_card_voice_test_wrongNum;
    private TextView act_card_voice_test_correctSign;
    private TextView act_card_voice_test_wrongSign;
    private TextView act_card_voice_test_counter;
    private TextView act_card_voice_test_glass;
    private CardView act_card_voice_test_cardView;

    private int totalQuestions;
    private int answerIndex;
    private int correctNum = 0;
    private int wrongNum = 0;
    private CardVOApp answerVO;

    private boolean isWrong = false;

    private TextToSpeech tts;
    private static final int REQ_SPEECH_TO_TEXT = 0;
    private static final int REQ_TTS_DATA_CHECK = 1;

    private String saidWord;
    private Vibrator vibrator;

    CountDownTimer timer = new CountDownTimer(13000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int counter = (int) millisUntilFinished / 1000;
            act_card_voice_test_counter.setText(String.valueOf(counter));
            if (counter <= 3) {
                act_card_voice_test_counter.setTextColor(Color.RED);
            } else {
                act_card_voice_test_counter.setTextColor(Color.DKGRAY);
            }
        }

        @Override
        public void onFinish() {

            if (!tempList.isEmpty()) {
                checkAnswer("", true);
                tempList.remove(tempList.get(answerIndex));
                nextQuestion();
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_voice_test);
        Toolbar toolbar = findViewById(R.id.act_card_voice_test_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Voice Test");
        tts = new TextToSpeech(this, this);

        Type listType = new TypeToken<List<CardVOApp>>() {
        }.getType();
        String jsonIn = getIntent().getStringExtra("cardVOAppList");
        cardVOAppList = new Gson().fromJson(jsonIn, listType);
        Collections.shuffle(cardVOAppList);
        tempList.addAll(cardVOAppList);
        member_card_group_id = getIntent().getStringExtra("member_card_group_id");

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        findViews();

        initializeQuestion();

    }

    @Override
    public void onClick(View view) {

        // 會提醒user可以開始語音輸入
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // 語音辨識依據web search語句
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra("android.speech.extra.DICTATION_MODE", true);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, false);
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,true);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);

        // 設定提示文字以提醒user開始語音輸入
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Please say:  " + answerVO.getWord() + "\n" + "Meaning: " + answerVO.getNative_explain());

        startActivityForResult(intent, REQ_SPEECH_TO_TEXT);

//        Button btn = (Button) view.findViewById(view.getId());
//        tts.speak(btn.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
//

    }

    @Override
    // TextToSpeech engine初始完畢會呼叫此方法, status: SUCCESS or ERROR.
    public void onInit(int status) {
        if (status == TextToSpeech.ERROR) {
            Util.showToast(this, "TTS Error!");
            return;
        }
        // 取得手機現行地區語言，並檢測是否有支援該語言
        int available = tts.isLanguageAvailable(Locale.getDefault());

        // TextToSpeech.LANG_AVAILABLE代表支援該語言
        // LANG_NOT_SUPPORTED代表不支援
        if (available == TextToSpeech.LANG_NOT_SUPPORTED) {
            Util.showToast(this, "TTS Language Not Supported!");
            return;
        }
        tts.setLanguage(Locale.US);

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, REQ_TTS_DATA_CHECK);
    }

    public void findViews() {
        activity_card_voice_test_ans_area = findViewById(R.id.act_card_voice_test_choice_ans_area);

        act_card_voice_test_speaker = findViewById(R.id.act_card_voice_test_speaker);
        act_card_voice_test_speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(answerVO.getWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        act_card_voice_test_said = findViewById(R.id.act_card_voice_test_said);
        act_card_voice_test_word = findViewById(R.id.act_card_voice_test_word);
        act_card_voice_test_pron = findViewById(R.id.act_card_voice_test_pron);
        act_card_voice_test_choice_voiceBtn = findViewById(R.id.act_card_voice_test_choice_voiceBtn);
        act_card_voice_test_choice_voiceBtn.setOnClickListener(this);

        act_card_voice_test_counter = findViewById(R.id.act_card_voice_test_counter);
        act_card_voice_test_total_left = findViewById(R.id.act_card_voice_test_total_left);
        act_card_voice_test_total_right = findViewById(R.id.act_card_voice_test_total_right);
        act_card_voice_test_correctNum = findViewById(R.id.act_card_voice_test_correctNum);
        act_card_voice_test_wrongNum = findViewById(R.id.act_card_voice_test_wrongNum);
        act_card_voice_test_correctSign = findViewById(R.id.act_card_voice_test_correctSign);
        act_card_voice_test_wrongSign = findViewById(R.id.act_card_voice_test_wrongSign);
        act_card_voice_test_cardView = findViewById(R.id.cardView6);
        act_card_voice_test_glass = findViewById(R.id.act_card_voice_test_glass);
    }

    public void updateQuestion() {

        btnAnimation();
        isWrong = false;
        updateTotalCounter();

        Random rnd = new Random();
        answerIndex = rnd.nextInt(tempList.size());
        answerVO = tempList.get(answerIndex);

        activity_card_voice_test_ans_area.setText(answerVO.getNative_explain());
        act_card_voice_test_word.setText(answerVO.getWord());
        act_card_voice_test_pron.setText(answerVO.getPhonetic_symbol());
        timer.start();
//        onClick(act_card_voice_test_choice_voiceBtn);
    }

    public void updateTotalCounter() {
        int newNum = Integer.valueOf(act_card_voice_test_total_right.getText().toString()) - tempList.size() + 1;
        act_card_voice_test_total_left.setText(String.valueOf(newNum));
    }

    public boolean checkAnswer(String ansWord, boolean isWrong) {
        if (ansWord.equalsIgnoreCase(answerVO.getWord())) {
            correctSignAnimation();
            vibrate(100);
            tempList.remove(tempList.get(answerIndex));
            if (isWrong == false) {
                updateCorrectCounter();
                updateToMCTTable(member_card_group_id, answerVO.getCard_id(), "0", "1", "-1");
            }
            return true;
        } else {
            wrongSignAnimation();
            if (isWrong == true) {
                updateWrongCounter();
                updateToMCTTable(member_card_group_id, answerVO.getCard_id(), "1", "0", "1");
            }
            return false;
        }
    }

    public void nextQuestion() {
        if (!tempList.isEmpty()) {
            updateQuestion();
        } else {
            disableQuestion();
        }
    }

    public void disableQuestion() {
        act_card_voice_test_cardView.setVisibility(View.INVISIBLE);
        float correctRate = (Float.valueOf(act_card_voice_test_correctNum.getText().toString()) / Float.valueOf(act_card_voice_test_total_right.getText().toString())) * 100;
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("測驗結束")
                .setMessage("恭喜完成! \n本次正確率: " + String.format("%.2f", correctRate) + "%")
                .setIcon(R.drawable.ic_school_black_24dp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                    }
                }).show();
    }

    public void updateCorrectCounter() {
        int newNum = Integer.valueOf(act_card_voice_test_correctNum.getText().toString());
        newNum += 1;
        act_card_voice_test_correctNum.setText(String.valueOf(newNum));
    }

    public void updateWrongCounter() {
        int newNum = Integer.valueOf(act_card_voice_test_wrongNum.getText().toString());
        newNum += 1;
        act_card_voice_test_wrongNum.setText(String.valueOf(newNum));
    }

    public void initializeQuestion() {

        totalQuestions = cardVOAppList.size();
        act_card_voice_test_total_right.setText(String.valueOf(totalQuestions));
        act_card_voice_test_correctNum.setText(String.valueOf(correctNum));
        act_card_voice_test_wrongNum.setText(String.valueOf(wrongNum));
        act_card_voice_test_correctSign.animate().alpha(0.0f).setDuration(0);
        act_card_voice_test_correctSign.setVisibility(View.VISIBLE);
        act_card_voice_test_wrongSign.animate().alpha(0.0f).setDuration(0);
        act_card_voice_test_wrongSign.setVisibility(View.VISIBLE);
        setGlassAni();
        updateQuestion();
    }

    public void correctSignAnimation() {
        act_card_voice_test_correctSign.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                act_card_voice_test_correctSign.animate().alpha(0.0f).setDuration(550);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void wrongSignAnimation() {
        act_card_voice_test_wrongSign.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                act_card_voice_test_wrongSign.animate().alpha(0.0f).setDuration(200);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void btnAnimation() {
        act_card_voice_test_said.setText("");
        activity_card_voice_test_ans_area.setVisibility(View.INVISIBLE);
        act_card_voice_test_word.setVisibility(View.INVISIBLE);
        act_card_voice_test_pron.setVisibility(View.INVISIBLE);
        act_card_voice_test_choice_voiceBtn.setVisibility(View.INVISIBLE);
        act_card_voice_test_speaker.setVisibility(View.INVISIBLE);

        act_card_voice_test_cardView.animate().alpha(0.0f).setStartDelay(0).scaleX(0).setDuration(250).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                act_card_voice_test_cardView.animate().alpha(1.0f).scaleX(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });


                activity_card_voice_test_ans_area.setVisibility(View.VISIBLE);
                act_card_voice_test_word.setVisibility(View.VISIBLE);
                act_card_voice_test_pron.setVisibility(View.VISIBLE);
                act_card_voice_test_choice_voiceBtn.setVisibility(View.VISIBLE);
                act_card_voice_test_speaker.setVisibility(View.VISIBLE);



            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        }).setStartDelay(0).start();
    }

    public void setGlassAni() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(1200);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        act_card_voice_test_glass.startAnimation(rotateAnimation);
    }

    public String updateToMCTTable(String member_card_group_id, String card_id, String wrong_number, String right_number, String choice_card_group) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "updateNum");
        jsonObject.addProperty("member_card_group_id", member_card_group_id);
        jsonObject.addProperty("card_id", card_id);
        jsonObject.addProperty("wrong_number", wrong_number);
        jsonObject.addProperty("right_number", right_number);
        jsonObject.addProperty("choice_card_group", choice_card_group);
        String result = "";
        try {
            new CommonTask(Util.URL + "MCTServletApp", jsonObject.toString()).execute();
//            result = new CommonTask(Util.URL + "MCTServletApp", jsonObject.toString()).execute().get();
            Log.e("voice test ",  result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_TTS_DATA_CHECK:
                // 如果沒有安裝TTS檔案，就必須引導user安裝
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL) {
                    Intent installIntent = new Intent();
                    // 會引導user到Play商店安裝
                    installIntent
                            .setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installIntent);
                }
                break;
            case REQ_SPEECH_TO_TEXT:
                if (resultCode == RESULT_OK) {
                    // 辨識結果會有多個相近的文字，並依照符合程度高低排序，第一個符合程度通常是最高
                    List<String> list = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = list.get(0);

                    saidWord = text;
                    act_card_voice_test_said.setText(saidWord);
//                    Util.showToast(th/is, text);
                    if (checkAnswer(act_card_voice_test_said.getText().toString(), false)) {
                        nextQuestion();
                    } else {
                        onClick(act_card_voice_test_choice_voiceBtn);
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        if (timer != null)
            timer.cancel();
    }

    public void vibrate(int milis){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(milis, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(milis);
        }    Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

    }
}
