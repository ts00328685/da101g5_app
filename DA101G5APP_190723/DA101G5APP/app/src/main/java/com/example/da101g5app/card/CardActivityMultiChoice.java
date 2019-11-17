package com.example.da101g5app.card;

import android.animation.Animator;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CardActivityMultiChoice extends AppCompatActivity implements View.OnClickListener {
    private TextView activity_card_multi_choice_ans_area;
    private Button activity_card_multi_choice_ansBtn1;
    private Button activity_card_multi_choice_ansBtn2;
    private Button activity_card_multi_choice_ansBtn3;
    private Button activity_card_multi_choice_ansBtn4;
    private String member_card_group_id;
    private List<CardVOApp> cardVOAppList;
    private List<CardVOApp> tempList = new ArrayList<CardVOApp>();
    private ArrayList<Button> rndChoiceButtons;

    private TextView act_card_multi_total_left;
    private TextView act_card_multi_total_right;
    private TextView act_card_multi_correctNum;
    private TextView act_card_multi_wrongNum;
    private TextView act_card_multi_correctSign;
    private TextView act_card_multi_wrongSign;
    private TextView act_card_multi_counter;
    private TextView act_card_multi_glass;
    private CardView act_card_multi_cardView;

    private int answerIndex;
    private int index = 0;
    private int correctNum = 0;
    private int wrongNum = 0;
    private CardVOApp answerVO;

    private boolean isWrong = false;

    private Vibrator vibrator;

    CountDownTimer timer = new CountDownTimer(6000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int counter = (int) millisUntilFinished / 1000;
            act_card_multi_counter.setText(String.valueOf(counter));
            if (counter <= 3) {
                act_card_multi_counter.setTextColor(Color.RED);
            } else {
                act_card_multi_counter.setTextColor(Color.DKGRAY);
            }
        }

        @Override
        public void onFinish() {

            if (!tempList.isEmpty()) {
                checkAnswer("");
                tempList.remove(tempList.get(answerIndex));
                nextQuestion();
            }
        }
    };

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_multi_choice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });

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

        Button btn = (Button) view.findViewById(view.getId());
        tts.speak(btn.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

        if (checkAnswer(btn.getText().toString())) {
            nextQuestion();
        } else {

        }
    }

    public void findViews() {
        activity_card_multi_choice_ans_area = findViewById(R.id.activity_card_multi_choice_ans_area);
        activity_card_multi_choice_ansBtn1 = findViewById(R.id.activity_card_multi_choice_ansBtn1);
        activity_card_multi_choice_ansBtn1.setOnClickListener(this);
        activity_card_multi_choice_ansBtn2 = findViewById(R.id.activity_card_multi_choice_ansBtn2);
        activity_card_multi_choice_ansBtn2.setOnClickListener(this);
        activity_card_multi_choice_ansBtn3 = findViewById(R.id.activity_card_multi_choice_ansBtn3);
        activity_card_multi_choice_ansBtn3.setOnClickListener(this);
        activity_card_multi_choice_ansBtn4 = findViewById(R.id.activity_card_multi_choice_ansBtn4);
        activity_card_multi_choice_ansBtn4.setOnClickListener(this);

        act_card_multi_counter = findViewById(R.id.act_card_multi_counter);
        act_card_multi_total_left = findViewById(R.id.act_card_multi_total_left);
        act_card_multi_total_right = findViewById(R.id.act_card_multi_total_right);
        act_card_multi_correctNum = findViewById(R.id.act_card_multi_correctNum);
        act_card_multi_wrongNum = findViewById(R.id.act_card_multi_wrongNum);
        act_card_multi_correctSign = findViewById(R.id.act_card_multi_correctSign);
        act_card_multi_wrongSign = findViewById(R.id.act_card_multi_wrongSign);
        act_card_multi_cardView = findViewById(R.id.cardView6);
        act_card_multi_glass = findViewById(R.id.act_card_multi_glass);
    }

    public void updateQuestion() {
        btnAnimation();
        isWrong = false;
        updateTotalCounter();

        Random rnd = new Random();
        Collections.shuffle(rndChoiceButtons);
        answerIndex = rnd.nextInt(tempList.size());
        answerVO = tempList.get(answerIndex);
        HashSet<String> rndFourChoice = new HashSet<String>();
        rndFourChoice.add(answerVO.getWord());

        //get four unrepeated words for buttons
        while (rndFourChoice.size() < 4) {
            int index = rnd.nextInt(cardVOAppList.size());
            rndFourChoice.add(cardVOAppList.get(index).getWord());
        }

        activity_card_multi_choice_ans_area.setText(tempList.get(answerIndex).getNative_explain());

        int index = 0;
        for (String words : rndFourChoice) {
            rndChoiceButtons.get(index).setText(words);
            index++;
        }

        timer.start();
    }

    public void updateTotalCounter() {
        int newNum = Integer.valueOf(act_card_multi_total_right.getText().toString()) - tempList.size() + 1;
        act_card_multi_total_left.setText(String.valueOf(newNum));
    }

    public boolean checkAnswer(String btnWord) {
        if (btnWord.equals(answerVO.getWord())) {
            vibrate(100);
            correctSignAnimation();
            tempList.remove(tempList.get(answerIndex));
            if (isWrong == false) {
                updateCorrectCounter();
                updateToMCTTable(member_card_group_id, answerVO.getCard_id(), "0", "1", "-1");
            }
            return true;
        } else {
            wrongSignAnimation();
            if (isWrong == false) {
                updateWrongCounter();
                updateToMCTTable(member_card_group_id, answerVO.getCard_id(), "1", "0", "1");
            }
            isWrong = true;
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
        act_card_multi_cardView.setVisibility(View.INVISIBLE);
        float correctRate = (Float.valueOf(act_card_multi_correctNum.getText().toString()) / Float.valueOf(act_card_multi_total_right.getText().toString())) * 100;
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
        int newNum = Integer.valueOf(act_card_multi_correctNum.getText().toString());
        newNum += 1;
        act_card_multi_correctNum.setText(String.valueOf(newNum));
    }

    public void updateWrongCounter() {
        int newNum = Integer.valueOf(act_card_multi_wrongNum.getText().toString());
        newNum += 1;
        act_card_multi_wrongNum.setText(String.valueOf(newNum));
    }

    public void initializeQuestion() {
        rndChoiceButtons = new ArrayList<Button>();
        rndChoiceButtons.add(activity_card_multi_choice_ansBtn1);
        rndChoiceButtons.add(activity_card_multi_choice_ansBtn2);
        rndChoiceButtons.add(activity_card_multi_choice_ansBtn3);
        rndChoiceButtons.add(activity_card_multi_choice_ansBtn4);
        act_card_multi_total_right.setText(String.valueOf(cardVOAppList.size()));
//        act_card_multi_total_left.setText(String.valueOf(index + 1));
        act_card_multi_correctNum.setText(String.valueOf(correctNum));
        act_card_multi_wrongNum.setText(String.valueOf(wrongNum));
        act_card_multi_correctSign.animate().alpha(0.0f).setDuration(0);
        act_card_multi_correctSign.setVisibility(View.VISIBLE);
        act_card_multi_wrongSign.animate().alpha(0.0f).setDuration(0);
        act_card_multi_wrongSign.setVisibility(View.VISIBLE);
        setGlassAni();
        updateQuestion();
    }

    public void correctSignAnimation() {
        act_card_multi_correctSign.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                act_card_multi_correctSign.animate().alpha(0.0f).setDuration(550);
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
        act_card_multi_wrongSign.animate().alpha(1.0f).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                act_card_multi_wrongSign.animate().alpha(0.0f).setDuration(200);
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
        activity_card_multi_choice_ans_area.setVisibility(View.INVISIBLE);
        for (int i = 0; i < rndChoiceButtons.size(); i++) {
            rndChoiceButtons.get(i).setVisibility(View.INVISIBLE);
        }
        act_card_multi_cardView.animate().alpha(0.0f).setStartDelay(0).scaleX(0).setDuration(250).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                act_card_multi_cardView.animate().alpha(1.0f).scaleX(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
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
                activity_card_multi_choice_ans_area.setVisibility(View.VISIBLE);
                for (int i = 0; i < rndChoiceButtons.size(); i++) {
                    rndChoiceButtons.get(i).setVisibility(View.VISIBLE);
                }
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
        act_card_multi_glass.startAnimation(rotateAnimation);
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
            Log.e("multi ",  result);
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

    public void vibrate(int milis){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(milis, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(milis);
        }    Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

    }
}
