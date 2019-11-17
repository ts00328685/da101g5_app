package com.example.da101g5app.task;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonTask extends AsyncTask<String, Integer, String> {
    private final static String TAG = "CommonTask";
    private String url, outStr;

    public CommonTask(String url, String outStr) {
        this.url = url;
        this.outStr = outStr;

//        progressDialog.setTitle("提示信息");
//        progressDialog.setMessage("登入中，請稍後......");
// 設置setCancelable(false); 表示我們不能取消這個彈出框，等下載完成之後再讓彈出框消失
//        progressDialog.setCancelable(false);
// 設置ProgressDialog樣式為圓圈的形式
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // 在onPreExecute()中我們讓ProgressDialog顯示出來

    }
    @Override
    protected String doInBackground(String... strings) {

        return getRemoteData();
    }
    protected void onPostExecute(String jsonIn) {
//            progressDialog.dismiss();
    }
    private String getRemoteData() {
        HttpURLConnection connection = null;
        StringBuilder inStr = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true); // allow inputs
            connection.setDoOutput(true); // allow outputs
            // 不知道請求內容大小時可以呼叫此方法將請求內容分段傳輸，設定0代表使用預設大小
            // 參考HttpURLConnection API的Posting Content部分
            connection.setChunkedStreamingMode(0);
            connection.setUseCaches(false); // do not use a cached copy
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "UTF-8");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bw.write(outStr);
            Log.e(TAG, "output: " + outStr);
            bw.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    inStr.append(line);
                }
            } else {
                Log.e(TAG, "response code: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        Log.e(TAG, "input: " + inStr.substring(0,inStr.length()/200));
        return inStr.toString();
    }
}
