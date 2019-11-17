package com.example.da101g5app.main;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.da101g5app.R;
import com.example.da101g5app.course.Course_orderVOApp;
import com.example.da101g5app.course.Time_orderVOApp;
import com.example.da101g5app.member.MemberVO;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.teacher.TeacherCardVO;
import com.example.da101g5app.teacher.Teacher_course_listVOApp;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeFragmentTabMyTeacherBottomFragment extends BottomSheetDialogFragment {

    private TeacherCardVO teacherCardVO;
    private MemberVO memberVO;

    private TextView fm_bs_ma_teacher_name;
    private Spinner fm_bs_ma_co_id;
    static private Spinner fm_bs_ma_hours;
    private Spinner fm_bs_ma_language;
    private Spinner fm_bs_ma_course_category;
    private TextView fm_bs_ma_co_remain_hour;
    private TextView fm_bs_ma_co_remain_hour_after;
    private Button fm_bs_ma_pickdate;
    private Button fm_bs_ma_picktime;
    static private TextView fm_bs_ma_start_date;
    static private TextView fm_bs_ma_start_time;
    static private TextView fm_bs_ma_end_date;
    static private TextView fm_bs_ma_end_time;
    private Button fm_bs_ma_sendOrd;

    private String teacher_id;
    private String member_id;
    private String teacher_name;

    private static int year, month, day, hour, minute, second;

    private CommonTask sendOrderTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheets_make_appointment, container, false);

        Bundle bundle = getArguments();
        teacher_id = bundle.getString("teacher_id");
        member_id = bundle.getString("member_id");
        teacher_name = bundle.getString("teacher_name");

        findView(view);

        return view;
    }

    public String getData(String action, String servlet) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", action);
        jsonObject.addProperty("member_id", member_id);
        jsonObject.addProperty("teacher_id", teacher_id);
        String result = null;
        try {
            result = new CommonTask(Util.URL + servlet, jsonObject.toString()).execute().get();
            Log.e("HFTMTBF", action + " " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getData(String action, String servlet, String additionalJsonObj) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", action);
        jsonObject.addProperty("member_id", member_id);
        jsonObject.addProperty("teacher_id", teacher_id);
        jsonObject.addProperty("time_orderVOApp", additionalJsonObj);
        String result = null;
        try {
            result = new CommonTask(Util.URL + servlet, jsonObject.toString()).execute().get();
            Log.e("getData wJ output", jsonObject.toString());
            Log.e("getData wtth Json", action + " " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public void findView(View view) {

        fm_bs_ma_teacher_name = view.findViewById(R.id.fm_bs_ma_teacher_name);
        fm_bs_ma_co_id = view.findViewById(R.id.fm_bs_ma_co_id);
        fm_bs_ma_hours = view.findViewById(R.id.fm_bs_ma_hours);
        fm_bs_ma_language = view.findViewById(R.id.fm_bs_ma_language);
        fm_bs_ma_course_category = view.findViewById(R.id.fm_bs_ma_course_category);
        fm_bs_ma_co_remain_hour = view.findViewById(R.id.fm_bs_ma_co_remain_hour);
        fm_bs_ma_co_remain_hour_after = view.findViewById(R.id.fm_bs_ma_co_remain_hour_after);
        fm_bs_ma_pickdate = view.findViewById(R.id.fm_bs_ma_pickdate);
        fm_bs_ma_picktime = view.findViewById(R.id.fm_bs_ma_picktime);
        fm_bs_ma_start_date = view.findViewById(R.id.fm_bs_ma_start_date);
        fm_bs_ma_start_time = view.findViewById(R.id.fm_bs_ma_start_time);
        fm_bs_ma_end_date = view.findViewById(R.id.fm_bs_ma_end_date);
        fm_bs_ma_end_time = view.findViewById(R.id.fm_bs_ma_end_time);
        fm_bs_ma_sendOrd = view.findViewById(R.id.fm_bs_ma_sendOrd);


        fm_bs_ma_teacher_name.setText(teacher_name);


        ArrayAdapter<String> fm_bs_ma_hoursAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, new String[]{"1", "2", "3"});
        fm_bs_ma_hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fm_bs_ma_hours.setAdapter(fm_bs_ma_hoursAdapter);

        List<String> list = new ArrayList<String>();
        String action = null;
        String servlet = null;
        String jsonIn = null;
        final Gson gson = new Gson();

        action = "getAllMyOneTeacherCourse";
        servlet = "Course_orderServletApp";
        jsonIn = getData(action, servlet);

        Type listType = new TypeToken<List<Course_orderVOApp>>() {
        }.getType();
        final List<Course_orderVOApp> course_orderVOAppList = gson.fromJson(jsonIn, listType);

        String[] course_order_ids = new String[course_orderVOAppList.size()];
        for (int i = 0; i < course_orderVOAppList.size(); i++) {
            course_order_ids[i] = course_orderVOAppList.get(i).getCourse_order_id();
        }

        ArrayAdapter<String> fm_bs_ma_co_idAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, course_order_ids);
        fm_bs_ma_co_idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fm_bs_ma_co_id.setAdapter(fm_bs_ma_co_idAdapter);

        fm_bs_ma_co_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fm_bs_ma_co_remain_hour.setText(String.valueOf(course_orderVOAppList.get(i).getRemain_hour()));
                int hour = Integer.valueOf(fm_bs_ma_hours.getSelectedItem().toString());
                int leftHour = course_orderVOAppList.get(i).getRemain_hour() - hour;
                if (leftHour >= 0) {
                    fm_bs_ma_co_remain_hour_after.setText(String.valueOf(leftHour));
                } else {
                    fm_bs_ma_hours.setSelection(0, true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        fm_bs_ma_hours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int hour = Integer.valueOf(fm_bs_ma_hours.getSelectedItem().toString());
                int leftHour = Integer.valueOf(fm_bs_ma_co_remain_hour.getText().toString()) - hour;
                fm_bs_ma_end_time.setText(String.format("%02d", Integer.valueOf(fm_bs_ma_start_time.getText().toString().substring(0, 2)) + hour) + fm_bs_ma_end_time.getText().toString().substring(2));
                if (leftHour < 0 || Integer.valueOf(fm_bs_ma_end_time.getText().toString().substring(0, 2)) > 24 || (fm_bs_ma_end_time.getText().toString().substring(0, 2).equals("24") && !(fm_bs_ma_end_time.getText().toString().substring(3).equals("00")))) {
                    Util.showToast(getActivity(), "時數不足或開始時間錯誤!");
                    fm_bs_ma_hours.setSelection(0, true);
                } else {
                    fm_bs_ma_co_remain_hour_after.setText(String.valueOf(leftHour));
                    fm_bs_ma_end_time.setText(String.format("%02d", Integer.valueOf(fm_bs_ma_start_time.getText().toString().substring(0, 2)) + hour) + fm_bs_ma_end_time.getText().toString().substring(2));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        action = "getAllOneTeacherCourse";
        servlet = "Teacher_course_listServletApp";
        jsonIn = getData(action, servlet);

        listType = new TypeToken<List<Teacher_course_listVOApp>>() {
        }.getType();
        final List<Teacher_course_listVOApp> teacher_course_listVOAppList = gson.fromJson(jsonIn, listType);

        Set<String> languagesSet = new HashSet<String>();
        Set<String> courseSet = new HashSet<String>();
        Map<String, Set<String>> lanCourseMap = new HashMap<String, Set<String>>();
        final Map<String, String> lanCourseIdMapp = new HashMap<String, String>();
        String prevLanguage = null;
        for (Teacher_course_listVOApp tcl : teacher_course_listVOAppList) {
            lanCourseIdMapp.put(tcl.getLanguage(), tcl.getLanguage_id());
            lanCourseIdMapp.put(tcl.getCourse_name(), tcl.getSort_course_id());
            languagesSet.add(tcl.getLanguage());
            if (tcl.getLanguage().equals(prevLanguage) || prevLanguage == null) {
                courseSet.add(tcl.getCourse_name());
            } else {
                courseSet.clear();
                courseSet.add(tcl.getCourse_name());
            }
            lanCourseMap.put(tcl.getLanguage(), new HashSet<String>(courseSet));
            prevLanguage = tcl.getLanguage();
        }
        final HashMap<String, HashSet<String>> lanCourseMapFinal = (HashMap) lanCourseMap;

        ArrayAdapter<String> fm_bs_ma_languageAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, new ArrayList(languagesSet));
        fm_bs_ma_languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fm_bs_ma_language.setAdapter(fm_bs_ma_languageAdapter);

        fm_bs_ma_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashSet<String> courses = lanCourseMapFinal.get(fm_bs_ma_language.getSelectedItem().toString());
                ArrayAdapter<String> fm_bs_ma_course_categoryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, new ArrayList<String>(courses));
                fm_bs_ma_course_categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fm_bs_ma_course_category.setAdapter(fm_bs_ma_course_categoryAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        fm_bs_ma_pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                FragmentManager fm = getChildFragmentManager();
                datePickerFragment.show(fm, "datePicker");
            }
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthStr = String.format("%02d", month);
        String dayStr = String.format("%02d", day+1);

        fm_bs_ma_start_date.setText(new StringBuilder().append(year).append("-").append(monthStr).append("-").append(dayStr).toString());
        fm_bs_ma_end_date.setText(fm_bs_ma_start_date.getText().toString());

        fm_bs_ma_start_time.setText("00:00");
        fm_bs_ma_end_time.setText("01:00");

        fm_bs_ma_picktime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                FragmentManager fm = getChildFragmentManager();
                timePickerFragment.show(fm, "timePicker");
            }
        });

        fm_bs_ma_sendOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Time_orderVOApp toVO = new Time_orderVOApp();
                    toVO.setTeacher_id(teacher_id);
                    toVO.setCourse_order_id(fm_bs_ma_co_id.getSelectedItem().toString());
                    toVO.setLanguage_id(lanCourseIdMapp.get(fm_bs_ma_language.getSelectedItem().toString()));
                    toVO.setLanguage(fm_bs_ma_language.getSelectedItem().toString());
                    toVO.setSort_course_id(lanCourseIdMapp.get(fm_bs_ma_course_category.getSelectedItem().toString()));
                    toVO.setC_state(0);
                    toVO.setC_judge(0);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String start_time = fm_bs_ma_start_date.getText().toString() + " " + fm_bs_ma_start_time.getText().toString() + ":00";
                    String end_time = fm_bs_ma_end_date.getText().toString() + " " + fm_bs_ma_end_time.getText().toString() + ":00";
                    java.sql.Timestamp sqlTimestampStart;
                    java.sql.Timestamp sqlTimestampEnd;
                    try {
                        sqlTimestampStart = new java.sql.Timestamp(sdf.parse(start_time).getTime());
                        toVO.setStart_time(sqlTimestampStart);
                        sqlTimestampEnd = new java.sql.Timestamp(sdf.parse(end_time).getTime());
                        toVO.setEnd_time(sqlTimestampEnd);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    String toVOJson = gsonBuilder.toJson(toVO);
                    String action = "add";
                    String servlet = "Time_orderServletApp";
                    String jsonIn = getData(action, servlet, toVOJson);

                    if ("success".equals(jsonIn)) {
                        Util.showToast(getActivity(), "送出申請成功!");
                    } else {
                        Util.showToast(getActivity(), "送出申請失敗!");
                    }

                }catch (Exception e) {
                    Util.showToast(getActivity(), "請確認填寫的資料!");
                }
            }
        });


//        fm_bs_td_hours.setSelection(0, true);
//        fm_bs_td_hours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                fm_bs_td_hours.setSelection(i, true);
//
//                int totalPrice = teacherCardVO.getCourse_price() * (i + 1);
//                fm_bs_td_total_price.setText(String.valueOf(totalPrice));
//                fm_bs_td_mem_point_left.setText(String.valueOf(memberVO.getMem_point().intValue() - totalPrice));
//                setMemPointLeftColor();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });


//        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
//        teacherCardVO = (TeacherCardVO) bundle.getSerializable("teacherCardVO");
//        if (teacherCardVO == null) {
//            teacherCardVO = (TeacherCardVO) bundle.getSerializable("myTeacherCardVO");
//        }
//
//        memberVO = (MemberVO) bundle.getSerializable("memberVO");
//        Gson gson = new Gson();
//
//        String member_id = memberVO.getMember_id();
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("action", "getOne");
//        jsonObject.addProperty("member_id", member_id);
//        String result = null;
//        try {
//            result = new CommonTask(Util.URL + "MemberServletApp", jsonObject.toString()).execute().get();
//            Log.e("TDBF", "getOneMember: " + result );
////            Util.showToast(getContext(), result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(result != null) {
//            memberVO = gson.fromJson(result, MemberVO.class);
//        }
//
//        fm_bs_td_teacher_name.setText(teacherCardVO.getMem_name());
//        fm_bs_td_price.setText(String.valueOf(teacherCardVO.getCourse_price()));
//        fm_bs_td_mem_point.setText(String.valueOf(memberVO.getMem_point().intValue()));
//
//        int totalPrice = teacherCardVO.getCourse_price();
//        fm_bs_td_total_price.setText(String.valueOf(totalPrice));
//        fm_bs_td_mem_point_left.setText(String.valueOf(memberVO.getMem_point().intValue() - totalPrice));
//        setMemPointLeftColor();
//    }
//

    }

    public void setLeftHourColor() {
        if (Integer.valueOf(fm_bs_ma_co_remain_hour_after.getText().toString()) < 0) {
            fm_bs_ma_co_remain_hour_after.setTextColor(Color.RED);
        } else {
            fm_bs_ma_co_remain_hour_after.setTextColor(Color.GRAY);
        }
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        // 改寫此方法以提供Dialog內容
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // 建立DatePickerDialog物件
            // this為OnDateSetListener物件
            // year、month、day會成為日期挑選器預選的年月日
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getActivity(), R.style.DatePickerDialogTheme, this, year, month, day+1);
            DatePicker datePicker = datePickerDialog.getDatePicker();
            datePicker.setMinDate(System.currentTimeMillis() + 24*60*60*1000);

            return datePickerDialog;
        }

        @Override
        // 日期挑選完成會呼叫此方法，並傳入選取的年月日
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            year = y;
            month = m + 1;
            day = d;
            String monthStr = String.format("%02d", month);
            String dayStr = String.format("%02d", day);
            fm_bs_ma_start_date.setText(new StringBuilder().append(year).append("-").append(monthStr).append("-").append(dayStr).toString());
            fm_bs_ma_end_date.setText(fm_bs_ma_start_date.getText().toString());
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        // 改寫此方法以提供Dialog內容
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // 建立TimePickerDialog物件
            // this為OnTimeSetListener物件
            // hour、minute會成為時間挑選器預選的時與分
            // false 設定是否為24小時制顯示
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    getActivity(), R.style.DatePickerDialogTheme, this, hour, 0, true);


            return timePickerDialog;
        }

        @Override
        // 時間挑選完成會呼叫此方法，並傳入選取的時與分
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            String hourStr = String.format("%02d", hour);
            String hourEndStr = String.format("%02d", hour + Integer.valueOf(fm_bs_ma_hours.getSelectedItem().toString()));
            String minuteStr = String.format("%02d", minute);
            if (Integer.valueOf(hourEndStr) > 24 || (Integer.valueOf(hourEndStr) == 24 && !minuteStr.equals("00"))) {
                Util.showToast(getActivity(), "時間錯誤!");
            } else {
                fm_bs_ma_start_time.setText(new StringBuilder().append(hourStr).append(":").append(minuteStr).toString());
                fm_bs_ma_end_time.setText(new StringBuilder().append(hourEndStr).append(":").append(minuteStr).toString());
            }
        }
    }
}
