package com.example.da101g5app.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da101g5app.R;
import com.example.da101g5app.card.CardFragment;
import com.example.da101g5app.commodity.CommodityFragment;
import com.example.da101g5app.course.CourseFragment;
import com.example.da101g5app.friend.FriendFragment;
import com.example.da101g5app.login.LoginActivity;
import com.example.da101g5app.member.MemberVO;
import com.example.da101g5app.point.Point_transActivity;
import com.example.da101g5app.task.ImageTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    public static final String TAG = "MainActivity";

    private HomeFragment homeFragment = new HomeFragment();
    private CommodityFragment commodityFragment = new CommodityFragment();
    private CardFragment cardFragment = new CardFragment();
    private FriendFragment friendFragment = new FriendFragment();
    private CourseFragment courseFragment = new CourseFragment();

    private ViewPager viewPager;
    private BottomNavigationView bottom_navigation;
    private TextView member_id;
    private TextView mem_nick;
    private TextView mem_email;
    private TextView mem_point;
    private ImageView mem_pic;

    private MemberVO memberVO;
    private ImageTask imageTask;

    Handler handler = new Handler();
    private ProgressDialog progressDialog;

    private Bundle bundle;
    private View header;

    private static final int REQ_TAKE_PICTURE = 0;
    private static final int REQ_LOGIN = 1;

    private Bitmap picture;
    private AsyncTask dataUploadTask;
    private File file;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            viewPager.setCurrentItem(item.getOrder());
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //上面TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Language Tutoring");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.bottom_navigation_viewPager);
        //添加viewPager事件监听（很容易忘）
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);

        //底部分頁
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //get the items in the nav_header layout
        NavigationView drawer_navigation = (NavigationView) drawer.findViewById(R.id.nav_view);
        drawer_navigation.setNavigationItemSelectedListener(this);

        header = drawer_navigation.getHeaderView(0);
//        Log.e(TAG, "onCreate: " + drawer_navigation.getHeaderCount());
        findViews(header);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return homeFragment;
                    case 1:
                        return courseFragment;
                    case 2:
                        return cardFragment;
                    case 3:
                        return friendFragment;
//                    case 4:
//                        return commodityFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.drawer_logout:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                Util.showToast(this, "Logged out!");
                this.finish();
                break;
            case R.id.drawer_sync:
                this.recreate();
                Util.showToast(this, "Syncing completed!");
                break;
            case R.id.drawer_points:
                intent = new Intent(this, Point_transActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        return true;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //页面滑动的时候，改变BottomNavigationView的Item高亮
        bottom_navigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            quitApp();
//            super.onBackPressed();
        }
    }

    public void findViews(View header) {

        bundle = getIntent().getExtras();
        memberVO = (MemberVO) bundle.getSerializable("memberVO");
        String account = memberVO.getMember_id();
        String password = memberVO.getMem_pwd();
        //先等待再繼續
        String jsonIn = null;
        //starting async com.example.da101g5app.task by sending URL, account, password
        try {
            jsonIn = new GetDataTask().execute(Util.URL + "loginhandlerapp", account, password).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        memberVO = createMemberVO(jsonIn);
        bundle.putSerializable("memberVO", memberVO);
        ((TextView) header.findViewById(R.id.nv_header_member_id)).setText(memberVO.getMember_id());
        ((TextView) header.findViewById(R.id.nv_header_mem_nick)).setText(memberVO.getMem_nick());
        ((TextView) header.findViewById(R.id.nv_header_mem_email)).setText(memberVO.getMem_ac());
        ((TextView) header.findViewById(R.id.nav_header_mem_point)).setText(String.valueOf((memberVO.getMem_point()).intValue()));
        ((ImageView) header.findViewById(R.id.nav_header_friend_pic)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (picture == null) {
////            showToast(this, R.string.msg_NotUploadWithoutPicture);
//                    return;
//                }
                takePicture();
            }
        });
    }

    private Bitmap Bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
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

        }

        private String getRemoteData(String url, String account, String password) {

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setDoInput(true); // allow inputs
                connection.setDoOutput(true); // allow outputs
                connection.setUseCaches(false); // do not use a cached copy
                connection.setConnectTimeout(7000);
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

    public MemberVO createMemberVO(String jsonIn) {
        Gson gson = new Gson();

        if ("failed".equals(jsonIn)) {
            Log.e(TAG, "onPostExecute: " + "?failed");
            Toast.makeText(MainActivity.this, "登入失敗", Toast.LENGTH_SHORT).show();
        } else if ("vogg".equals(jsonIn)) {

            Log.e(TAG, "onPostExecute: " + "?vogg");
            Toast.makeText(MainActivity.this, "你它嗎VO有問題", Toast.LENGTH_SHORT).show();

        } else {

            JsonObject jsonObject = gson.fromJson(jsonIn, JsonObject.class);
            memberVO.setMem_ac(jsonObject.get("mem_ac").getAsString());
            memberVO.setMem_name(jsonObject.get("mem_name").getAsString());
            memberVO.setMem_nick(jsonObject.get("mem_nick").getAsString());
            memberVO.setMem_point(Double.valueOf(jsonObject.get("mem_point").getAsString()));
            memberVO.setMem_pwd(jsonObject.get("mem_pwd").getAsString());
            memberVO.setMember_id(jsonObject.get("member_id").getAsString());

            String url = Util.URL + "MemberServletApp";
            int imageSize = imageSize = getResources().getDisplayMetrics().widthPixels / 4;
            imageTask = new ImageTask(url, memberVO.getMember_id(), imageSize, ((ImageView) header.findViewById(R.id.nav_header_friend_pic)));
            imageTask.execute();
//                Log.e(TAG, "onPostExecute: " + memberVO.getFriend_pic().toString());
        }

        return memberVO;
    }

    public void quitApp() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("退出APP")
                .setMessage("確定要退出嗎?")
                .setIcon(R.drawable.ic_exit_to_app_black_24dp)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }


    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定存檔路徑
        file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        file = new File(file, "picture.jpg");
        // targeting Android 7.0 (API level 24) and higher,
        // storing images using a FileProvider.
        // passing a file:// URI across a package boundary causes a FileUriExposedException.
        Uri contentUri = FileProvider.getUriForFile(
                this, getPackageName() + ".provider", file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

        if (isIntentAvailable(this, intent)) {
            startActivityForResult(intent, REQ_TAKE_PICTURE);
        } else {
//            showToast(this, "No camera available!");
        }
    }


    public boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
                // 手機拍照App拍照完成後可以取得照片圖檔
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    // inSampleSize值即為縮放的倍數 (數字越大縮越多)
                    opt.inSampleSize = Util.getImageScale(file.getPath(), 320, 640);
                    picture = BitmapFactory.decodeFile(file.getPath(), opt);
                    ((ImageView) header.findViewById(R.id.nav_header_friend_pic)).setImageBitmap(picture);
                    byte[] image = Util.bitmapToPNG(picture);
                    if (networkConnected()) {
                        dataUploadTask = new DataUploadTask().execute(Util.URL, "MemberServletApp", image);
                    } else {
//                        showToast(this, R.string.msg_NoNetwork);
                    }
        }
    }

    // check if the device connect to the network
    private boolean networkConnected() {
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private String getRemoteData(String url, String jsonOut) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setDoInput(true); // allow inputs
        connection.setDoOutput(true); // allow outputs
        connection.setUseCaches(false); // do not use a cached copy
        connection.setConnectTimeout(200000);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("charset", "UTF-8");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write(jsonOut);
        Log.d(TAG, "jsonOut: " + jsonOut);
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
            Log.d(TAG, "response code: " + responseCode);
        }
        connection.disconnect();
        Log.d(TAG, "jsonIn: " + jsonIn);
        progressDialog.cancel();
        return jsonIn.toString();
    }


    @Override
    protected void onPause() {
        if (dataUploadTask != null) {
            dataUploadTask.cancel(true);
        }
        super.onPause();
    }

    private void showToast(Context context, int messageId) {
        Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show();
    }

    private final static int REQ_PERMISSIONS = 0;

    @Override
    protected void onStart() {
        super.onStart();
        askPermissions();
    }

    private void askPermissions() {
        String[] permissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int result = ContextCompat.checkSelfPermission(this, permissions[0]);
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    REQ_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSIONS:
                String text = "";
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        text += permissions[i] + "\n";
                    }
                }
                if (!text.isEmpty()) {
//                    text += getString(R.string.text_NotGranted);
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private class DataUploadTask extends AsyncTask<Object, Integer, Void> {

        @Override
        // invoked on the UI thread immediately after the task is executed.
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        // invoked on the background thread immediately after onPreExecute()
        protected Void doInBackground(Object... params) {
            String url = params[0].toString();
            String servlet = params[1].toString();
            byte[] image = (byte[]) params[2];
            String jsonIn;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("member_id", memberVO.getMember_id());
            jsonObject.addProperty("action", "uploadImage");
            jsonObject.addProperty("imageBase64", Base64.encodeToString(image, Base64.DEFAULT));
            try {
                jsonIn = getRemoteData(url + servlet, jsonObject.toString());
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                return null;
            }
//            Gson gson = new Gson();
//            JsonObject jObject = gson.fromJson(jsonIn, JsonObject.class);
//            byte[] pic = Base64.decode(jObject.get("imageBase64").getAsString(), Base64.DEFAULT);
//            return pic;
            return null;
        }

        @Override
        /*
         * invoked on the UI thread after the background computation finishes.
         * The result of the background computation is passed to this step as a
         * parameter.
         */
        protected void onPostExecute(Void s) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(pic, 0, pic.length);
//            ImageView ivResultImage = findViewById(R.id.nav_header_friend_pic);
//            ivResultImage.setImageBitmap(bitmap);
            progressDialog.cancel();
        }
    }

}
