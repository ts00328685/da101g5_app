package com.example.da101g5app.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.da101g5app.main.MainActivity;
import com.example.da101g5app.member.MemberVO;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int EDITTEXT_DELAY = 300;
    public static final int BUTTON_DELAY = 350;
    public static final int VIEW_DELAY = 400;
    private Button login;
    private EditText etAccount;
    private EditText etPassword;
    private Context context = this;
    private String TAG = "test";
    public static final String PREF_NAME = "account";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.da101g5app.R.layout.activity_login);
        ImageView logoImageView = findViewById(com.example.da101g5app.R.id.img_logo);
        ViewGroup container = findViewById(com.example.da101g5app.R.id.container_login);

        ViewCompat.animate(logoImageView)
                .scaleX(0.0f).scaleY(0.0f)
                .setDuration(0).start();
        ViewCompat.animate(logoImageView)
                .scaleX(1.2f).scaleY(1.2f)
                .translationY(-250)
                .setStartDelay(200)
                .setDuration(3000)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();
        ViewCompat.animate(logoImageView)
                .setStartDelay(0).setDuration(2000)
                .scaleX(1.0f).scaleY(1.0f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();


        for (int i = 0; i < container.getChildCount(); i++) {
            View view = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (view instanceof EditText) {
                viewAnimator = ViewCompat.animate(view)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((EDITTEXT_DELAY * i) + 500)
                        .setDuration(500);
            } else if (view instanceof Button) {
                viewAnimator = ViewCompat.animate(view)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((BUTTON_DELAY * i) + 500)
                        .setDuration(500);
            } else {
                viewAnimator = ViewCompat.animate(view)
                        .translationY(50).alpha(1)
                        .setStartDelay((VIEW_DELAY * i) + 100)
                        .setDuration(1000);
            }

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }

        //get the init ui elements
        login = findViewById(com.example.da101g5app.R.id.login);
        etAccount = findViewById(com.example.da101g5app.R.id.etAccount);
        etPassword = findViewById(com.example.da101g5app.R.id.etPassword);

        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        etAccount.setText(preferences.getString("account", "M00001"));
        etPassword.setText(preferences.getString("password", "000000"));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("Notice");
                progressDialog.setMessage("Logging in, please wait....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                savePref(account, password);

                if (Util.isNetworkConnected(context)) {
                    //先等待再繼續
                    String jsonStr = null;
                    //starting async com.example.da101g5app.task by sending URL, account, password
                    new GetDataTask().execute(Util.URL + "loginhandlerapp", account, password);
                } else {
                    Util.showToast(context, "no network connection!");
                }


            }
        });

        TranslateAnimation translateAnimation = getTranslateAnimation();
        translateAnimation.setInterpolator(
                AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_decelerate_interpolator));
        logoImageView.startAnimation(translateAnimation);


    }

    private TranslateAnimation getTranslateAnimation() {
        // 球移動的距離
        float distance = 50;
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, distance, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        return translateAnimation;
    }
    private void savePref(String account, String password) {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        preferences.edit()
                .putString("account", account)
                .putString("password", password)
                .apply();
    }

    //connecting to servlet
    private class GetDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 在onPreExecute()中我們讓ProgressDialog顯示出來

        }

        @Override
        protected String doInBackground(String... strings) {
            String jsonIn = null;
            jsonIn = getRemoteData(strings[0], strings[1], strings[2]);
            Log.e(TAG, jsonIn);
            return jsonIn;
        }

        @Override
        protected void onPostExecute(String jsonIn) {
//            tvResult.setText(s);

            Gson gson = new Gson();

            if ("failed".equals(jsonIn)) {
                Log.e(TAG, "onPostExecute: " + "?failed");
                Toast.makeText(context, "登入失敗", Toast.LENGTH_SHORT).show();
            } else if ("vogg".equals(jsonIn)) {

                Log.e(TAG, "onPostExecute: " + "?vogg");
                Toast.makeText(context, "你它嗎VO有問題", Toast.LENGTH_SHORT).show();

            } else {

                MemberVO memberVO = new MemberVO();
                JsonObject jsonObject = gson.fromJson(jsonIn, JsonObject.class);
                memberVO.setMem_ac(jsonObject.get("mem_ac").getAsString());
                memberVO.setMem_name(jsonObject.get("mem_name").getAsString());
                memberVO.setMem_nick(jsonObject.get("mem_nick").getAsString());
                memberVO.setMem_point(Double.valueOf(jsonObject.get("mem_point").getAsString()));
                memberVO.setMem_pwd(jsonObject.get("mem_pwd").getAsString());
                memberVO.setMember_id(jsonObject.get("member_id").getAsString());
//                Log.e(TAG, "onPostExecute: " + memberVO.getFriend_pic().toString());

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("memberVO", memberVO);

                intent.putExtras(bundle);



                startActivity(intent);
                ((AppCompatActivity) context).finish();

            }
            progressDialog.dismiss();
        }

        private String getRemoteData(String url, String account, String password) {

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setDoInput(true); // allow inputs
                connection.setDoOutput(true); // allow outputs
                connection.setUseCaches(false); // do not use a cached copy
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("charset", "UTF-8");

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("account", account);
                jsonObject.addProperty("password", password);
                String jsonOut = jsonObject.toString();

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                bw.write(jsonOut);
                Log.e(TAG, "jsonOut: " + jsonOut);
                bw.close();

                int responseCode = connection.getResponseCode();
                StringBuilder jsonIn = new StringBuilder();
                if (responseCode == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        jsonIn.append(line);
                    }
                } else {
                    Log.e(TAG, "response code: " + responseCode);
                    return "failed";
                }
                connection.disconnect();
                Log.e(TAG, "jsonIn: " + jsonIn);
                return jsonIn.toString();

            } catch (IOException e) {
                return "failed";
            }
        }
    }
}
