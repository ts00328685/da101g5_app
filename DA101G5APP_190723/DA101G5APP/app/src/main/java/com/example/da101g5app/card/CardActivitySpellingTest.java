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
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CardActivitySpellingTest extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {
    private TextView activity_card_voice_test_ans_area;
    private Button act_card_spelling_test_speaker;
    private TextView act_card_spelling_test_word;
    private TextView act_card_spelling_test_pron;
    private Button act_card_spelling_test_refresh;

    private String member_card_group_id;
    private List<CardVOApp> cardVOAppList;
    private List<CardVOApp> tempList = new ArrayList<CardVOApp>();
    private ArrayList<Button> rndChoiceButtons;

    private TextView act_card_spelling_test_total_left;
    private TextView act_card_spelling_test_total_right;
    private TextView act_card_spelling_test_correctNum;
    private TextView act_card_spelling_test_wrongNum;
    private TextView act_card_spelling_test_correctSign;
    private TextView act_card_spelling_test_wrongSign;
    private TextView act_card_spelling_test_counter;
    private TextView act_card_spelling_test_glass;
    private CardView act_card_spelling_test_cardView;

    private int totalQuestions;
    private int answerIndex;
    private int correctNum = 0;
    private int wrongNum = 0;
    private CardVOApp answerVO;

    private boolean isWrong = false;

    private TextToSpeech tts;
    private static final int REQ_SPEECH_TO_TEXT = 0;
    private static final int REQ_TTS_DATA_CHECK = 1;

    private RecyclerView act_card_spelling_test_rv;
    private StringBuilder underlines;

    Vibrator vibrator;
// Vibrate for 500 milliseconds

    private LetterListAdapter letterListAdapter;
    private List<String> letterList = new ArrayList<String>();
    private int index;

    CountDownTimer timer = new CountDownTimer(21000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int counter = (int) millisUntilFinished / 1000;
            act_card_spelling_test_counter.setText(String.valueOf(counter));
            if (counter <= 3) {
                act_card_spelling_test_counter.setTextColor(Color.RED);
            } else {
                act_card_spelling_test_counter.setTextColor(Color.DKGRAY);
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
        setContentView(R.layout.activity_card_spelling_test);
        Toolbar toolbar = findViewById(R.id.act_card_spelling_test_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Spelling Test");
        tts = new TextToSpeech(this, this);

        Type listType = new TypeToken<List<CardVOApp>>() {
        }.getType();
        String jsonIn = getIntent().getStringExtra("cardVOAppList");
        cardVOAppList = new Gson().fromJson(jsonIn, listType);
        Collections.shuffle(cardVOAppList);
        tempList.addAll(cardVOAppList);
        member_card_group_id = getIntent().getStringExtra("member_card_group_id");

        findViews();

        act_card_spelling_test_rv = findViewById(R.id.act_card_spelling_test_rv);
        act_card_spelling_test_rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 5);
        act_card_spelling_test_rv.setLayoutManager(layoutManager);
        act_card_spelling_test_rv.setLayoutManager(
                new StaggeredGridLayoutManager(
                        5, StaggeredGridLayoutManager.VERTICAL));

        initializeQuestion();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View view) {


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
        activity_card_voice_test_ans_area = findViewById(R.id.act_card_spelling_test_choice_ans_area);
        act_card_spelling_test_refresh = findViewById(R.id.act_card_spelling_test_refresh);
        act_card_spelling_test_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRecyclerView();
                vibrate(250);
            }
        });

        act_card_spelling_test_speaker = findViewById(R.id.act_card_spelling_test_speaker);
        act_card_spelling_test_speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(answerVO.getWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        act_card_spelling_test_word = findViewById(R.id.act_card_spelling_test_word);
        act_card_spelling_test_word.setLetterSpacing(0.1f);
        act_card_spelling_test_pron = findViewById(R.id.act_card_spelling_test_pron);

        act_card_spelling_test_counter = findViewById(R.id.act_card_spelling_test_counter);
        act_card_spelling_test_total_left = findViewById(R.id.act_card_spelling_test_total_left);
        act_card_spelling_test_total_right = findViewById(R.id.act_card_spelling_test_total_right);
        act_card_spelling_test_correctNum = findViewById(R.id.act_card_spelling_test_correctNum);
        act_card_spelling_test_wrongNum = findViewById(R.id.act_card_spelling_test_wrongNum);
        act_card_spelling_test_correctSign = findViewById(R.id.act_card_spelling_test_correctSign);
        act_card_spelling_test_wrongSign = findViewById(R.id.act_card_spelling_test_wrongSign);
        act_card_spelling_test_cardView = findViewById(R.id.cardView6);
        act_card_spelling_test_glass = findViewById(R.id.act_card_spelling_test_glass);
    }

    public void updateQuestion() {

        btnAnimation();
        isWrong = false;
        updateTotalCounter();

        Random rnd = new Random();
        answerIndex = rnd.nextInt(tempList.size());
        answerVO = tempList.get(answerIndex);

        updateRecyclerView();

        activity_card_voice_test_ans_area.setText(answerVO.getNative_explain());
        act_card_spelling_test_pron.setText(answerVO.getPhonetic_symbol());
        timer.start();
    }

    public void updateTotalCounter() {
        int newNum = Integer.valueOf(act_card_spelling_test_total_right.getText().toString()) - tempList.size() + 1;
        act_card_spelling_test_total_left.setText(String.valueOf(newNum));
    }

    public boolean checkAnswer(String ansWord, boolean isWrong) {
        if (ansWord.equalsIgnoreCase(answerVO.getWord())) {
            correctSignAnimation();
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
        act_card_spelling_test_cardView.setVisibility(View.INVISIBLE);
        float correctRate = (Float.valueOf(act_card_spelling_test_correctNum.getText().toString()) / Float.valueOf(act_card_spelling_test_total_right.getText().toString())) * 100;
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
        int newNum = Integer.valueOf(act_card_spelling_test_correctNum.getText().toString());
        newNum += 1;
        act_card_spelling_test_correctNum.setText(String.valueOf(newNum));
    }

    public void updateWrongCounter() {
        int newNum = Integer.valueOf(act_card_spelling_test_wrongNum.getText().toString());
        newNum += 1;
        act_card_spelling_test_wrongNum.setText(String.valueOf(newNum));
    }

    public void initializeQuestion() {

        totalQuestions = cardVOAppList.size();
        act_card_spelling_test_total_right.setText(String.valueOf(totalQuestions));
        act_card_spelling_test_correctNum.setText(String.valueOf(correctNum));
        act_card_spelling_test_wrongNum.setText(String.valueOf(wrongNum));
        act_card_spelling_test_correctSign.animate().alpha(0.0f).setDuration(0);
        act_card_spelling_test_correctSign.setVisibility(View.VISIBLE);
        act_card_spelling_test_wrongSign.animate().alpha(0.0f).setDuration(0);
        act_card_spelling_test_wrongSign.setVisibility(View.VISIBLE);
        setGlassAni();
        updateQuestion();
    }

    public void correctSignAnimation() {
        act_card_spelling_test_correctSign.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                act_card_spelling_test_correctSign.animate().alpha(0.0f).setDuration(550);
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
        act_card_spelling_test_wrongSign.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                act_card_spelling_test_wrongSign.animate().alpha(0.0f).setDuration(200);
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
        activity_card_voice_test_ans_area.setVisibility(View.INVISIBLE);
        act_card_spelling_test_word.setVisibility(View.INVISIBLE);
        act_card_spelling_test_pron.setVisibility(View.INVISIBLE);
        act_card_spelling_test_speaker.setVisibility(View.INVISIBLE);
        act_card_spelling_test_rv.setVisibility(View.INVISIBLE);
        act_card_spelling_test_refresh.setVisibility(View.INVISIBLE);

        act_card_spelling_test_cardView.animate().alpha(0.0f).setStartDelay(0).scaleX(0).setDuration(250).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                act_card_spelling_test_cardView.animate().alpha(1.0f).scaleX(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
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
                act_card_spelling_test_word.setVisibility(View.VISIBLE);
                act_card_spelling_test_pron.setVisibility(View.VISIBLE);
                act_card_spelling_test_speaker.setVisibility(View.VISIBLE);
                act_card_spelling_test_rv.setVisibility(View.VISIBLE);
                act_card_spelling_test_refresh.setVisibility(View.VISIBLE);


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
        act_card_spelling_test_glass.startAnimation(rotateAnimation);
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
            Log.e("voice test ", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    private class LetterListAdapter extends RecyclerView.Adapter<CardActivitySpellingTest.LetterListAdapter.ViewHolder> {
        private List<String> adapterLetterList = letterList;
        private Context context;
        private LayoutInflater layoutInflater;


        private LetterListAdapter(List<String> letterList) {
            this.context = CardActivitySpellingTest.this;
            layoutInflater = LayoutInflater.from(context);
            this.adapterLetterList = letterList;


        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cv_letter_letter;

            private ViewHolder(View view) {
                super(view);
                cv_letter_letter = view.findViewById(R.id.cv_letter_letter);
            }
        }

        @Override
        public int getItemCount() {
            return letterList.size();
        }

        @Override
        public CardActivitySpellingTest.LetterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_letter, parent, false);
            return new CardActivitySpellingTest.LetterListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CardActivitySpellingTest.LetterListAdapter.ViewHolder holder, int position) {
            final String letter = letterList.get(position);
            holder.cv_letter_letter.setText(letter);
            CardView cv = (CardView) holder.cv_letter_letter.getParent();
            cv.setVisibility(View.VISIBLE);
            holder.cv_letter_letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    vibrate( 70);

                    underlines.replace(underlines.length() - index, underlines.length() - index + 1 , letter);
                    act_card_spelling_test_word.setText(underlines.toString());
                    CardView cv = (CardView) view.getParent();
                    cv.setVisibility(View.INVISIBLE);
                    index--;
//                    letterList.remove(letter);
//                    letterListAdapter.notifyDataSetChanged();

                    if (index == 0) {
                        if (checkAnswer(act_card_spelling_test_word.getText().toString(), false)) {
                            vibrate( 200);
                            nextQuestion();
                        } else {
                            updateRecyclerView();
                        }
                    }
                }
            });
        }
    }

    public void updateRecyclerView() {
        letterList.clear();
        underlines = new StringBuilder();
        for (int i = 0; i < answerVO.getWord().length(); i++) {
            letterList.add(answerVO.getWord().substring(i, i + 1));
            underlines.append("_");
        }
        index = letterList.size();
        Collections.shuffle(letterList);
        act_card_spelling_test_word.setText(underlines.toString());
        if (letterListAdapter == null) {
            letterListAdapter = new CardActivitySpellingTest.LetterListAdapter(letterList);
            act_card_spelling_test_rv.setAdapter(letterListAdapter);
        }
        letterListAdapter.notifyDataSetChanged();
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
