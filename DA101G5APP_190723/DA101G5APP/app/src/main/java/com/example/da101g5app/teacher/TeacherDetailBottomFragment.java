package com.example.da101g5app.teacher;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.course.Course_orderVO;
import com.example.da101g5app.member.MemberVO;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TeacherDetailBottomFragment extends BottomSheetDialogFragment {
    private Spinner fm_bs_td_hours;
    private TextView fm_bs_td_teacher_name;
    private TextView fm_bs_td_price;
    private TextView fm_bs_td_total_price;
    private TextView fm_bs_td_mem_point;
    private TextView fm_bs_td_mem_point_left;

    private TeacherCardVO teacherCardVO;
    private MemberVO memberVO;

    private Button fm_bs_td_sendOrder;

    private CommonTask sendOrderTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottomsheets_teacher_detail, container, false);

        findView(view);

        fm_bs_td_sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int point_left = Integer.valueOf(fm_bs_td_mem_point_left.getText().toString());
                if(point_left < 0) {
                    Util.showToast(getContext(), "點數不足!");
                } else {

                    Course_orderVO course_orderVO = new Course_orderVO();
                    course_orderVO.setMember_id(memberVO.getMember_id());
                    course_orderVO.setBuy_time(Integer.valueOf(fm_bs_td_hours.getSelectedItem().toString()));
                    course_orderVO.setRe_appointment(0);
                    course_orderVO.setSpend_point(Integer.valueOf(fm_bs_td_total_price.getText().toString()));
                    course_orderVO.setTeacher_id(teacherCardVO.getTeacher_id());
                    course_orderVO.setRemain_hour(course_orderVO.getBuy_time());

                    Gson gson = new Gson();

                    String course_orderVOJson = gson.toJson(course_orderVO);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("action", "add");
                    jsonObject.addProperty("course_orderVO", course_orderVOJson);

                    try {
                        String result = new CommonTask(Util.URL + "Course_orderServletApp", jsonObject.toString()).execute().get();
                        Log.e("TDBF", "onClick: " + result );
                        if (result.equals("success")) {
                            Util.showToast(getContext(), "購買成功!");
                            dismiss();
                        } else {
                            Util.showToast(getContext(), "購買失敗!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }
        });


        return view;
    }

    public void findView(View view) {


        fm_bs_td_hours = view.findViewById(R.id.fm_bs_td_hours);
        fm_bs_td_teacher_name = view.findViewById(R.id.fm_bs_td_teacher_name);
        fm_bs_td_price = view.findViewById(R.id.fm_bs_td_price);
        fm_bs_td_total_price = view.findViewById(R.id.fm_bs_td_total_price);
        fm_bs_td_mem_point = view.findViewById(R.id.fm_bs_td_mem_point);
        fm_bs_td_mem_point_left = view.findViewById(R.id.fm_bs_td_mem_point_left);

        fm_bs_td_sendOrder = view.findViewById(R.id.fm_bs_td_sendOrd);
        // 在執行setOnItemSelectedListener()之前先呼叫setSelection(position, animate)
        // 可避免一開始就執行OnItemSelectedListener.onItemSelected()
        fm_bs_td_hours.setSelection(0, true);

        // 直接由程式碼動態產生Spinner做法
        String[] spNum = new String[100];
        for (int i = 0; i < spNum.length; i++) {
            spNum[i] = String.valueOf(i + 1);
        }
        // ArrayAdapter用來管理整個選項的內容與樣式，android.R.layout.simple_spinner_item為內建預設樣式
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), R.layout.spinner_item, spNum);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fm_bs_td_hours.setAdapter(adapter);
        fm_bs_td_hours.setSelection(0, true);
        fm_bs_td_hours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fm_bs_td_hours.setSelection(i, true);

                int totalPrice = teacherCardVO.getCourse_price() * (i + 1);
                fm_bs_td_total_price.setText(String.valueOf(totalPrice));
                fm_bs_td_mem_point_left.setText(String.valueOf(memberVO.getMem_point().intValue() - totalPrice));
                setMemPointLeftColor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        teacherCardVO = (TeacherCardVO) bundle.getSerializable("teacherCardVO");
        if (teacherCardVO == null) {
            teacherCardVO = (TeacherCardVO) bundle.getSerializable("myTeacherCardVO");
        }

        memberVO = (MemberVO) bundle.getSerializable("memberVO");
        Gson gson = new Gson();

        String member_id = memberVO.getMember_id();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getOne");
        jsonObject.addProperty("member_id", member_id);
        String result = null;
        try {
            result = new CommonTask(Util.URL + "MemberServletApp", jsonObject.toString()).execute().get();
            Log.e("TDBF", "getOneMember: " + result );
//            Util.showToast(getContext(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result != null) {
            memberVO = gson.fromJson(result, MemberVO.class);
        }

        fm_bs_td_teacher_name.setText(teacherCardVO.getMem_name());
        fm_bs_td_price.setText(String.valueOf(teacherCardVO.getCourse_price()));
        fm_bs_td_mem_point.setText(String.valueOf(memberVO.getMem_point().intValue()));

        int totalPrice = teacherCardVO.getCourse_price();
        fm_bs_td_total_price.setText(String.valueOf(totalPrice));
        fm_bs_td_mem_point_left.setText(String.valueOf(memberVO.getMem_point().intValue() - totalPrice));
        setMemPointLeftColor();
    }

    public void setMemPointLeftColor(){
        if(Integer.valueOf(fm_bs_td_mem_point_left.getText().toString()) < 0 ) {
            fm_bs_td_mem_point_left.setTextColor(Color.RED);
        } else {
            fm_bs_td_mem_point_left.setTextColor(Color.GRAY);
        }
    }
}
