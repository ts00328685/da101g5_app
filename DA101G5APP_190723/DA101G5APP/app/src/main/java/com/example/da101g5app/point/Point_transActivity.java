package com.example.da101g5app.point;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.member.MemberVO;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Point_transActivity extends AppCompatActivity {

    private CommonTask getPointTransVOTask;
    private static final String TAG = "point_transAct";
    private Context context = this;

    private Button act_point_btn;
    private EditText act_point_point;

    private MemberVO memberVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_trans);

        RecyclerView recyclerView = findViewById(R.id.activity_point_trans_rv);
        //設定每個List是否為固定尺寸
        recyclerView.setHasFixedSize(true);
        //產生一個LinearLayoutManger
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //設定LayoutManager
        recyclerView.setLayoutManager(layoutManager);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        memberVO = (MemberVO) bundle.getSerializable("memberVO");

        String member_id = memberVO.getMember_id();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("action", "getAllPTS");
        jsonObject.addProperty("member_id", member_id);

        String jsonOut = jsonObject.toString();

        List<Point_transVOApp> ptsList = null;
        try {

            String jsonIn = new CommonTask(Util.URL + "Point_transServletApp", jsonOut).execute().get();
            Type listType = new TypeToken<List<Point_transVOApp>>() {
            }.getType();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            ptsList = gson.fromJson(jsonIn, listType);
            Log.e(TAG, "onCreate: "+ptsList.toString());

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        if (ptsList == null || ptsList.isEmpty()) {
            Util.showToast(this, "ptsList  Not Found");
        } else {
            recyclerView.setAdapter(new Point_transActivity.Point_transVOAppAdapter(ptsList));
        }


        act_point_btn = findViewById(R.id.act_save_point_btn);
        act_point_point = findViewById(R.id.act_pt_point);

        act_point_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double points = 0.0;
                try {
                    points = Double.valueOf(act_point_point.getText().toString());
                    if(points <=0){
                        throw new Exception();
                    }
                } catch(Exception e) {
                    Util.showToast(Point_transActivity.this, "格式錯誤!");
                    e.printStackTrace();
                    return;
                }

                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("action", "add");
                jsonObject.addProperty("member_id", memberVO.getMember_id());
                jsonObject.addProperty("points", points);


                String jsonOut = jsonObject.toString();
                String jsonIn = null;
                try {
                    jsonIn = new CommonTask(Util.URL + "Point_transServletApp", jsonOut).execute().get();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                if ("success".equals(jsonIn)) {
                    Util.showToast(Point_transActivity.this, "儲值成功!");
                    recreate();
                } else {
                    Util.showToast(Point_transActivity.this, "儲值失敗!");
                }


            }
        });


    }
    private class Point_transVOAppAdapter extends RecyclerView.Adapter<Point_transActivity.Point_transVOAppAdapter.ViewHolder> {
        private List<Point_transVOApp> ptsList;

        private Point_transVOAppAdapter(List<Point_transVOApp> ptsList) {
            this.ptsList = ptsList;
        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cv_pts_trans_time;
            private TextView cv_pts_trans_point;

            private ViewHolder(View view) {
                super(view);
                cv_pts_trans_time = view.findViewById(R.id.cv_pts_trans_time);
                cv_pts_trans_point = view.findViewById(R.id.cv_pts_trans_amount);
            }
        }

        @Override
        public int getItemCount() {
            return ptsList.size();
        }

        @Override
        public Point_transActivity.Point_transVOAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_point_trans_record, parent, false);
            return new Point_transActivity.Point_transVOAppAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Point_transActivity.Point_transVOAppAdapter.ViewHolder holder, int position) {
            //將資料注入到View裡
            final Point_transVOApp point_transVOApp = ptsList.get(position);

            int point_record = point_transVOApp.getPoint_record().intValue();
            holder.cv_pts_trans_point.setText(String.valueOf(point_record));
            String transdate = point_transVOApp.getTransdate_app().toString();
            holder.cv_pts_trans_time.setText(transdate.substring(0,transdate.length()-2));

            if (point_record >= 0) {
                holder.cv_pts_trans_point.setTextColor(Color.GREEN);
                holder.cv_pts_trans_point.setText("+"+holder.cv_pts_trans_point.getText().toString());
            } else {
                holder.cv_pts_trans_point.setTextColor(Color.RED);
            }



//            // itemView為ViewHolder內建屬性(指的就是每一列)
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(Point_transActivity.this, cardVO.getWord(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }


}
