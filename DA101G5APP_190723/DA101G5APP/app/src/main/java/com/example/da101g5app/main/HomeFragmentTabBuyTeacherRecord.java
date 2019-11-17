package com.example.da101g5app.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.course.Course_orderVOApp;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.task.ImageTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.da101g5app.login.LoginActivity.PREF_NAME;

public class HomeFragmentTabBuyTeacherRecord extends Fragment {

    private CommonTask getCourse_orderVOAppTask;
    private ImageTask getTeacher_picTask;
    private static final String TAG = "TabBuyTeacherRecord";
    List<Course_orderVOApp> course_orderVOAppVOList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter course_orderVOAppVOListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_tab_buyteacherrecord, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_tab_buy_teacher_record_rv);
        //設定每個List是否為固定尺寸
        recyclerView.setHasFixedSize(true);

        //產生一個LinearLayoutManger
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //設定LayoutManager
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout = view.findViewById(R.id.home_teacher_tab_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<Course_orderVOApp> newList = getCourse_orderVOAppVOList();
                course_orderVOAppVOList.clear();
                course_orderVOAppVOList.addAll(newList);
                if(course_orderVOAppVOListAdapter == null) {
                    course_orderVOAppVOListAdapter = new HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter(course_orderVOAppVOList);
                    recyclerView.setAdapter(course_orderVOAppVOListAdapter);
                }
                course_orderVOAppVOListAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        course_orderVOAppVOList = getCourse_orderVOAppVOList();

        if (course_orderVOAppVOList == null || course_orderVOAppVOList.isEmpty()) {
            Util.showToast(getActivity(), "course_orderVOAppVOList  Not Found");
        } else {
            course_orderVOAppVOListAdapter = new HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter(course_orderVOAppVOList);
            recyclerView.setAdapter(course_orderVOAppVOListAdapter);
        }

        return view;
    }

    public List<Course_orderVOApp> getCourse_orderVOAppVOList() {

        List<Course_orderVOApp> list = new ArrayList<Course_orderVOApp>();


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getAllRecords");
        SharedPreferences preferences = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        jsonObject.addProperty("member_id", preferences.getString("account", ""));

        String jsonOut = jsonObject.toString();

        getCourse_orderVOAppTask = new CommonTask(Util.URL + "Course_orderServletApp", jsonOut);

        try {

            String jsonIn = getCourse_orderVOAppTask.execute().get();
            Type listType = new TypeToken<List<Course_orderVOApp>>() {
            }.getType();

            list = new Gson().fromJson(jsonIn, listType);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return list;
    }

    private class Course_orderVOAppVOListAdapter extends RecyclerView.Adapter<HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter.ViewHolder> {
        private List<Course_orderVOApp> course_orderVOAppVOList;
        private Context context;
        private int imageSize;
        private LayoutInflater layoutInflater;

        private Course_orderVOAppVOListAdapter(List<Course_orderVOApp> course_orderVOAppVOList) {
            this.context = getContext();
            layoutInflater = LayoutInflater.from(context);
            this.course_orderVOAppVOList = course_orderVOAppVOList;
            /* 螢幕寬度除以4當作將圖的尺寸 */
            imageSize = getResources().getDisplayMetrics().widthPixels / 4;

        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cv_buy_teacher_record_co_id;
            private TextView cv_buy_teacher_record_co_amount;
            private TextView cv_buy_teacher_record_co_unit_price;
            private TextView cv_buy_teacher_record_teacher_name;
            private TextView cv_buy_teacher_record_co_total_hour;
            private TextView cv_buy_teacher_record_co_remaining_hour;

            private ViewHolder(View view) {
                super(view);
                cv_buy_teacher_record_co_id = view.findViewById(R.id.cv_buyTeacherTab_co_id);
                cv_buy_teacher_record_co_amount = view.findViewById(R.id.cv_buyTeacherTab_co_total);
                cv_buy_teacher_record_co_unit_price = view.findViewById(R.id.cv_buyTeacherTab_co_unit_price);
                cv_buy_teacher_record_teacher_name = view.findViewById(R.id.cv_buyTeacherTab_teacherName);
                cv_buy_teacher_record_co_total_hour = view.findViewById(R.id.cv_buyTeacherTab_buy_hour);
                cv_buy_teacher_record_co_remaining_hour = view.findViewById(R.id.cv_buyTeacherTab_remain_hour);

            }
        }

        @Override
        public int getItemCount() {
            return course_orderVOAppVOList.size();
        }

        @Override
        public HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_buy_teacher_record, parent, false);
            return new HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HomeFragmentTabBuyTeacherRecord.Course_orderVOAppVOListAdapter.ViewHolder holder, int position) {
            //將資料注入到View裡
            final Course_orderVOApp Course_orderVOApp = course_orderVOAppVOList.get(position);
            holder.cv_buy_teacher_record_co_id.setText(Course_orderVOApp.getCourse_order_id());
            holder.cv_buy_teacher_record_co_amount.setText(String.valueOf(Course_orderVOApp.getSpend_point()));
            holder.cv_buy_teacher_record_co_unit_price.setText(String.valueOf(Course_orderVOApp.getSpend_point()/Course_orderVOApp.getBuy_time()));
            holder.cv_buy_teacher_record_teacher_name.setText(Course_orderVOApp.getTeacher_name());
            holder.cv_buy_teacher_record_co_total_hour.setText(String.valueOf(Course_orderVOApp.getBuy_time()));
            holder.cv_buy_teacher_record_co_remaining_hour.setText(String.valueOf(Course_orderVOApp.getRemain_hour()));

            // itemView為ViewHolder內建屬性(指的就是每一列)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

}
