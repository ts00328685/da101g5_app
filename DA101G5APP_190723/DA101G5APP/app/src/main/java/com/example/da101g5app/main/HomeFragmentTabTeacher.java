package com.example.da101g5app.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.task.ImageTask;
import com.example.da101g5app.teacher.TeacherCardVO;
import com.example.da101g5app.teacher.TeacherDetailActivity;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static android.content.Context.MODE_PRIVATE;
import static com.example.da101g5app.login.LoginActivity.PREF_NAME;

public class HomeFragmentTabTeacher extends Fragment {

    private CommonTask getTeacherCardVOTask;
    private ImageTask getTeacher_picTask;
    private static final String TAG = "HomeFragmentTabTeacher";

    private SwipeRefreshLayout swipeRefreshLayout;

    List<TeacherCardVO> teacherCardVOList;
    private HomeFragmentTabTeacher.TeacherCardVOListAdapter teacherCardVOListAdapter;
    private FloatingActionButton filterBtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_tab_teacher, container, false);
        filterBtn = view.findViewById(R.id.fragment_home_tab_filter_fab);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_tab_teacher_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout = view.findViewById(R.id.home_teacher_tab_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<TeacherCardVO> newList = getTeacherCardVOList();
                teacherCardVOList.clear();
                teacherCardVOList.addAll(newList);
                teacherCardVOListAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        teacherCardVOList = getTeacherCardVOList();

        if (teacherCardVOList == null || teacherCardVOList.isEmpty()) {
            Util.showToast(getActivity(), "teacherCardVOList  Not Found");
        } else {
            teacherCardVOListAdapter = new HomeFragmentTabTeacher.TeacherCardVOListAdapter(teacherCardVOList);
            recyclerView.setAdapter(teacherCardVOListAdapter);
        }


        filterBtn.setOnClickListener(new View.OnClickListener() {
            int indexForFilter = 0;

            @Override
            public void onClick(View view) {

                int filterCode = indexForFilter % 3;
                List<TeacherCardVO> filterResultList = null;
                switch (filterCode) {
                    case 0:
                        filterResultList = new ArrayList<TeacherCardVO>();
                        filterResultList = teacherCardVOList.stream()
                                .filter(tvo -> (tvo.getAppraisal_accum() / ((tvo.getAppraisal_count() == 0) ? 1 : tvo.getAppraisal_count())) >= 3.0f).collect(Collectors.toList());
                        teacherCardVOList.clear();
                        teacherCardVOList.addAll(filterResultList);
                        teacherCardVOListAdapter.notifyDataSetChanged();
                        Util.showToast(getActivity(),"Filtered by Stars >= 3");
                        break;
                    case 1:
                        filterResultList = new ArrayList<TeacherCardVO>();
                        filterResultList = teacherCardVOList.stream()
                                .filter(tvo -> tvo.getCourse_price() <= 500).collect(Collectors.toList());
                        teacherCardVOList.clear();
                        teacherCardVOList.addAll(filterResultList);
                        teacherCardVOListAdapter.notifyDataSetChanged();
                        Util.showToast(getActivity(),"Filtered by Price  <= 500");
                        break;
                    case 2:
                        filterResultList = new ArrayList<TeacherCardVO>();
                        filterResultList = teacherCardVOList.stream()
                                .filter(tvo -> tvo.getMem_nick().contains("小")).collect(Collectors.toList());
                        teacherCardVOList.clear();
                        teacherCardVOList.addAll(filterResultList);
                        teacherCardVOListAdapter.notifyDataSetChanged();
                        Util.showToast(getActivity(),"Filtered by Nickname contains 小");
                        break;

                }
                indexForFilter++;
            }
        });

        return view;
    }

    public List<TeacherCardVO> getTeacherCardVOList() {

        List<TeacherCardVO> list = new ArrayList<TeacherCardVO>();
        //retrieve data from server
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getAll");
        SharedPreferences preferences = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        jsonObject.addProperty("member_id", preferences.getString("account", ""));

        String jsonOut = jsonObject.toString();

        getTeacherCardVOTask = new CommonTask(Util.URL + "TeacherServletApp", jsonOut);

        try {

            String jsonIn = getTeacherCardVOTask.execute().get();
            Type listType = new TypeToken<List<TeacherCardVO>>() {
            }.getType();

            list = new Gson().fromJson(jsonIn, listType);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return list;
    }

    private class TeacherCardVOListAdapter extends RecyclerView.Adapter<HomeFragmentTabTeacher.TeacherCardVOListAdapter.ViewHolder> {
        private List<TeacherCardVO> teacherCardVOList;
        private Context context;
        private int imageSize;
        private LayoutInflater layoutInflater;

        private TeacherCardVOListAdapter(List<TeacherCardVO> teacherCardVOList) {
            this.context = getContext();
            layoutInflater = LayoutInflater.from(context);
            this.teacherCardVOList = teacherCardVOList;
            /* 螢幕寬度除以4當作將圖的尺寸 */
            imageSize = getResources().getDisplayMetrics().widthPixels / 4;

        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView cv_teacher_pic;
            private RatingBar cv_teacher_star;
            private TextView cv_teacher_name;
            private TextView cv_teacher_language;
            private TextView cv_teacher_country;
            private TextView cv_teacher_price;
            private TextView cv_teacher_nick;


            private ViewHolder(View view) {
                super(view);
                cv_teacher_pic = view.findViewById(R.id.cv_teacher_pic);
                cv_teacher_star = view.findViewById(R.id.cv_teacher_star);
                cv_teacher_name = view.findViewById(R.id.cv_teacher_name);
                cv_teacher_language = view.findViewById(R.id.cv_teacher_language);
                cv_teacher_country = view.findViewById(R.id.cv_teacher_country);
                cv_teacher_price = view.findViewById(R.id.cv_teacher_price);
                cv_teacher_nick = view.findViewById(R.id.cv_teacher_nick);

            }
        }

        @Override
        public int getItemCount() {
            return teacherCardVOList.size();
        }

        @Override
        public HomeFragmentTabTeacher.TeacherCardVOListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_teacher, parent, false);
            return new HomeFragmentTabTeacher.TeacherCardVOListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HomeFragmentTabTeacher.TeacherCardVOListAdapter.ViewHolder holder, int position) {
            //將資料注入到View裡
            final TeacherCardVO teacherCardVO = teacherCardVOList.get(position);
            holder.cv_teacher_name.setText(teacherCardVO.getMem_name());
            holder.cv_teacher_star.setRating(Float.valueOf(teacherCardVO.getAppraisal_accum()) / Float.valueOf(teacherCardVO.getAppraisal_count()));
            String url = Util.URL + "TeacherServletApp";
            getTeacher_picTask = new ImageTask(url, teacherCardVO.getTeacher_id(), imageSize, holder.cv_teacher_pic);
            getTeacher_picTask.execute();
            holder.cv_teacher_country.setText(teacherCardVO.getMem_country());
            holder.cv_teacher_language.setText(teacherCardVO.getLanguage());
            holder.cv_teacher_price.setText(String.valueOf(teacherCardVO.getCourse_price()));
            holder.cv_teacher_nick.setText(teacherCardVO.getMem_nick());


            // itemView為ViewHolder內建屬性(指的就是每一列)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), TeacherDetailActivity.class);
                    Bundle bundle = getActivity().getIntent().getExtras();
                    bundle.putSerializable("teacherCardVO", teacherCardVO);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

}
