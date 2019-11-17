package com.example.da101g5app.course;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.da101g5app.login.LoginActivity.PREF_NAME;

public class CourseFragmentTabAppointmentRecord extends Fragment {

    private CommonTask getTime_orderVOAppTask;
    private static final String TAG = "CourseFmTabAtRd";
    List<Time_orderVOApp> time_orderVOAppVOList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter time_orderVOAppVOListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_tab_appointment_records, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_course_tab_appointment_records_rv);
        //設定每個List是否為固定尺寸
        recyclerView.setHasFixedSize(true);

        //產生一個LinearLayoutManger
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //設定LayoutManager
        recyclerView.setLayoutManager(layoutManager);



        time_orderVOAppVOList = getTime_orderVOAppVOList();

        if (time_orderVOAppVOList == null || time_orderVOAppVOList.isEmpty()) {
            Util.showToast(getActivity(), "time_orderVOAppVOList  Not Found");
        } else {
            time_orderVOAppVOListAdapter = new CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter(time_orderVOAppVOList);
            recyclerView.setAdapter(time_orderVOAppVOListAdapter);
        }

        swipeRefreshLayout = view.findViewById(R.id.course_tab_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<Time_orderVOApp> newList = getTime_orderVOAppVOList();
                time_orderVOAppVOList.clear();
                time_orderVOAppVOList.addAll(newList);

                if(time_orderVOAppVOListAdapter == null){
                    time_orderVOAppVOListAdapter = new CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter(time_orderVOAppVOList);
                    recyclerView.setAdapter(time_orderVOAppVOListAdapter);
                }
                time_orderVOAppVOListAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    public List<Time_orderVOApp> getTime_orderVOAppVOList() {

        List<Time_orderVOApp> list = new ArrayList<Time_orderVOApp>();


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getAllApp");
        SharedPreferences preferences = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        jsonObject.addProperty("member_id", preferences.getString("account", ""));

        String jsonOut = jsonObject.toString();

        getTime_orderVOAppTask = new CommonTask(Util.URL + "Time_orderServletApp", jsonOut);

        try {

            String jsonIn = getTime_orderVOAppTask.execute().get();
            Log.e(TAG, "getTime_orderVOAppVOList: " + jsonIn );

            Type listType = new TypeToken<List<Time_orderVOApp>>() {
            }.getType();
            Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            list = gsonBuilder.fromJson(jsonIn, listType);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return list;
    }

    private class Time_orderVOAppVOListAdapter extends RecyclerView.Adapter<CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter.ViewHolder> {
        private List<Time_orderVOApp> time_orderVOAppVOList;
        private Context context;
        private LayoutInflater layoutInflater;

        private Time_orderVOAppVOListAdapter(List<Time_orderVOApp> time_orderVOAppVOList) {
            this.context = getContext();
            layoutInflater = LayoutInflater.from(context);
            this.time_orderVOAppVOList = time_orderVOAppVOList;

        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cv_appointment_to_id;
            private TextView cv_appointment_teacherName;
            private TextView cv_appointment_course_name;
            private TextView cv_appointment_category;
            private TextView cv_appointment_state;
            private TextView cv_appointment_total_hour;
            private TextView cv_appointment_start_time;
            private TextView cv_appointment_end_time;
            private Button cv_appointment_confirm_course;
            private Button cv_appointment_confirm_appointment;

            private ViewHolder(View view) {
                super(view);
                cv_appointment_to_id = view.findViewById(R.id.cv_appointment_to_id);
                cv_appointment_teacherName = view.findViewById(R.id.cv_appointment_teacherName);
                cv_appointment_course_name = view.findViewById(R.id.cv_appointment_course_name);
                cv_appointment_category = view.findViewById(R.id.cv_appointment_category);
                cv_appointment_state = view.findViewById(R.id.cv_appointment_state);
                cv_appointment_total_hour = view.findViewById(R.id.cv_appointment_total_hour);
                cv_appointment_start_time = view.findViewById(R.id.cv_appointment_start_time);
                cv_appointment_end_time = view.findViewById(R.id.cv_appointment_end_time);
                cv_appointment_confirm_course = view.findViewById(R.id.cv_appointment_confirm_course);
                cv_appointment_confirm_appointment = view.findViewById(R.id.cv_appointment_confirm_appointment);
            }
        }

        @Override
        public int getItemCount() {
            return time_orderVOAppVOList.size();
        }

        @Override
        public CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_appointment_record, parent, false);
            return new CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CourseFragmentTabAppointmentRecord.Time_orderVOAppVOListAdapter.ViewHolder holder, int position) {
            //將資料注入到View裡
            final Time_orderVOApp time_orderVOApp = time_orderVOAppVOList.get(position);
            holder.cv_appointment_to_id.setText(time_orderVOApp.getTime_order_id());
            holder.cv_appointment_category.setText(time_orderVOApp.getCourse());
            holder.cv_appointment_course_name.setText(time_orderVOApp.getLanguage());


            String state="";
            switch(time_orderVOApp.getC_state()) {
                case 0:
                    state = "老師尚未確認預約";
                    holder.cv_appointment_state.setTextColor(Color.parseColor("#e6b800"));
                    holder.cv_appointment_confirm_appointment.setVisibility(View.VISIBLE);
                    holder.cv_appointment_confirm_course.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    state = "老師已確認預約";
                    holder.cv_appointment_state.setTextColor(Color.parseColor("#008000"));
                    holder.cv_appointment_confirm_course.setVisibility(View.VISIBLE);
                    holder.cv_appointment_confirm_appointment.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    state = "已完成課程";
                    holder.cv_appointment_state.setTextColor(Color.parseColor("#0066cc"));
                    holder.cv_appointment_confirm_appointment.setVisibility(View.INVISIBLE);
                    holder.cv_appointment_confirm_course.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    state = "老師已確認預約";
                    holder.cv_appointment_state.setTextColor(Color.parseColor("#008000"));
                    holder.cv_appointment_confirm_course.setVisibility(View.VISIBLE);
                    holder.cv_appointment_confirm_appointment.setVisibility(View.INVISIBLE);
                    break;
            }

            holder.cv_appointment_state.setText(state);

            String start_time = time_orderVOApp.getStart_time().toString();
            holder.cv_appointment_start_time.setText(start_time.substring(0, start_time.length()-5));
            String end_time =time_orderVOApp.getEnd_time().toString();
            holder.cv_appointment_end_time.setText(end_time.substring(0, end_time.length()-5));
            holder.cv_appointment_teacherName.setText(time_orderVOApp.getTeacher_name());
            Log.e(TAG, "onBindViewHolder: "+start_time);
            int startHour = Integer.valueOf(start_time.substring(11,13));
            int endHour = Integer.valueOf(end_time.substring(11,13));
            if (endHour == 0 ){
                endHour +=24;
            }
            holder.cv_appointment_total_hour.setText(String.valueOf(endHour - startHour));

            // itemView為ViewHolder內建屬性(指的就是每一列)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            holder.cv_appointment_confirm_appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.cv_appointment_state.getText().toString().equals("老師尚未確認預約") ){

                       String result = updateTOState("updateTOState", "Time_orderServletApp", time_orderVOApp.getCourse_order_id()
                       , time_orderVOApp.getTime_order_id(), holder.cv_appointment_total_hour.getText().toString(), "1", "30");

                       if("success".equals(result)) {
                           Util.showToast(getActivity(), "確認預約成功!");
                           holder.cv_appointment_state.setText("老師已確認預約");
                           holder.cv_appointment_state.setTextColor(Color.parseColor("#008000"));
                           holder.cv_appointment_confirm_appointment.setVisibility(View.INVISIBLE);
                           holder.cv_appointment_confirm_course.setVisibility(View.VISIBLE);
                       } else {
                           Util.showToast(getActivity(), "確認預約失敗!");
                       }

                    } else {
                        Util.showToast(getActivity(),"狀態不符!");
                    }
                }
            });

            holder.cv_appointment_confirm_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.cv_appointment_state.getText().toString().equals("老師已確認預約") ){


                        Dialog rankDialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                        rankDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        rankDialog.setContentView(R.layout.rank_dialog);
                        rankDialog.setCancelable(true);
                        RatingBar  ratingBar = (RatingBar)rankDialog.findViewById(R.id.dialog_ratingbar);

                        TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
                        text.setText("請留下您的評價");

                        Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
                        updateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Float rating = ratingBar.getRating();
                                String result = updateTOState("updateTOState", "Time_orderServletApp", time_orderVOApp.getCourse_order_id()
                                        , time_orderVOApp.getTime_order_id(), holder.cv_appointment_total_hour.getText().toString(), "2", rating.toString());

                                if("success".equals(result)) {
                                    Util.showToast(getActivity(), "完課確認成功!");
                                    holder.cv_appointment_state.setText("已完成課程");
                                    holder.cv_appointment_state.setTextColor(Color.parseColor("#0066cc"));
                                    holder.cv_appointment_confirm_course.setVisibility(View.INVISIBLE);

                                } else {
                                    Util.showToast(getActivity(), "完課確認失敗!");
                                }




                                rankDialog.dismiss();
                            }
                        });
                        //now that the dialog is set up, it's time to show it
                        rankDialog.show();






                    } else {
                        Util.showToast(getActivity(),"狀態不符!");
                    }
                }
            });


        }
    }
    public String updateTOState(String action, String servlet, String course_order_id, String time_order_id, String total_hour, String c_state, String c_judge) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", action);
        jsonObject.addProperty("time_order_id", time_order_id);
        jsonObject.addProperty("total_hour", total_hour);
        jsonObject.addProperty("course_order_id", course_order_id);
        jsonObject.addProperty("c_state", c_state);
        jsonObject.addProperty("c_judge", c_judge);
        String result = "";
        try {
            result = new CommonTask(Util.URL + servlet, jsonObject.toString()).execute().get();
            Log.e("CourseFmAptm", action + " " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
