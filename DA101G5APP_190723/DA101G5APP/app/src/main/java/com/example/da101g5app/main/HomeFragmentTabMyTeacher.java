package com.example.da101g5app.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.task.ImageTask;
import com.example.da101g5app.teacher.MyTeacherCardVO;
import com.example.da101g5app.teacher.TeacherDetailActivity;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.da101g5app.login.LoginActivity.PREF_NAME;

public class HomeFragmentTabMyTeacher extends Fragment {

    private CommonTask getMyTeacherCardVOTask;
    private ImageTask getTeacher_picTask;
    private static final String TAG = "TabMyTeacher";
    List<MyTeacherCardVO> myTeacherCardVOList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter myTeacherCardVOListAdapter;
    private SharedPreferences preferences;
    private Button cv_my_teacher_ma_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tab_myteacher, container, false);



        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_tab_teacher_rv);
        //設定每個List是否為固定尺寸
        recyclerView.setHasFixedSize(true);

        //產生一個LinearLayoutManger
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        //設定LayoutManager
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        2, StaggeredGridLayoutManager.VERTICAL));
        myTeacherCardVOList = getMyTeacherCardVOList();

        if (myTeacherCardVOList == null || myTeacherCardVOList.isEmpty()) {
            Util.showToast(getActivity(), "myTeacherCardVOList  Not Found");
        } else {
            myTeacherCardVOListAdapter = new HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter(myTeacherCardVOList);
            recyclerView.setAdapter(myTeacherCardVOListAdapter);
        }

        swipeRefreshLayout = view.findViewById(R.id.home_teacher_tab_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<MyTeacherCardVO> newList = getMyTeacherCardVOList();
                myTeacherCardVOList.clear();
                myTeacherCardVOList.addAll(newList);
                if(myTeacherCardVOListAdapter != null) {
                    myTeacherCardVOListAdapter.notifyDataSetChanged();
                } else {
                    myTeacherCardVOListAdapter = new HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter(myTeacherCardVOList);
                    recyclerView.setAdapter(myTeacherCardVOListAdapter);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    public List<MyTeacherCardVO> getMyTeacherCardVOList() {

        List<MyTeacherCardVO> list = new ArrayList<MyTeacherCardVO>();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getMyTeacher");
        preferences = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        jsonObject.addProperty("member_id", preferences.getString("account", ""));

        String jsonOut = jsonObject.toString();

        getMyTeacherCardVOTask = new CommonTask(Util.URL + "TeacherServletApp", jsonOut);

        try {

            String jsonIn = getMyTeacherCardVOTask.execute().get();
            Type listType = new TypeToken<List<MyTeacherCardVO>>() {
            }.getType();

            list = new Gson().fromJson(jsonIn, listType);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return list;
    }

    private class MyTeacherCardVOListAdapter extends RecyclerView.Adapter<HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter.ViewHolder> {
        private List<MyTeacherCardVO> myTeacherCardVOList;
        private Context context;
        private int imageSize;
        private LayoutInflater layoutInflater;

        private MyTeacherCardVOListAdapter(List<MyTeacherCardVO> myTeacherCardVOList) {
            this.context = getContext();
            layoutInflater = LayoutInflater.from(context);
            this.myTeacherCardVOList = myTeacherCardVOList;
            /* 螢幕寬度除以4當作將圖的尺寸 */
            imageSize = getResources().getDisplayMetrics().widthPixels / 4;

        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView cv_my_teacher_pic;
            private RatingBar cv_my_teacher_star;
            private TextView cv_my_teacher_name;
            private TextView cv_my_teacher_language;
            //            private TextView cv_teacher_country;
//            private TextView cv_teacher_price;
            private TextView cv_my_teacher_nick;
            private TextView cv_my_teacher_remain_hour;
            private Button cv_my_teacher_ma_btn;

            private ViewHolder(View view) {
                super(view);
                cv_my_teacher_pic = view.findViewById(R.id.cv_my_teacher_pic);
                cv_my_teacher_star = view.findViewById(R.id.cv_my_teacher_star);
                cv_my_teacher_name = view.findViewById(R.id.cv_my_teacher_name);
                cv_my_teacher_language = view.findViewById(R.id.cv_my_teacher_language);
//                cv_teacher_country = view.findViewById(R.id.cv_teacher_country);
//                cv_teacher_price = view.findViewById(R.id.cv_teacher_price);
                cv_my_teacher_nick = view.findViewById(R.id.cv_my_teacher_nick);
                cv_my_teacher_remain_hour = view.findViewById(R.id.cv_my_teacher_remain_hour);
                cv_my_teacher_ma_btn = view.findViewById(R.id.cv_my_teacher_ma_btn);

            }
        }

        @Override
        public int getItemCount() {
            return myTeacherCardVOList.size();
        }

        @Override
        public HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_my_teacher, parent, false);
            return new HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final HomeFragmentTabMyTeacher.MyTeacherCardVOListAdapter.ViewHolder holder, int position) {
            //將資料注入到View裡
            final MyTeacherCardVO myTeacherCardVO = myTeacherCardVOList.get(position);
            holder.cv_my_teacher_name.setText(myTeacherCardVO.getMem_name());
            holder.cv_my_teacher_star.setRating(Float.valueOf(myTeacherCardVO.getAppraisal_accum()) / Float.valueOf(myTeacherCardVO.getAppraisal_count()));
            String url = Util.URL + "TeacherServletApp";
            getTeacher_picTask = new ImageTask(url, myTeacherCardVO.getTeacher_id(), imageSize, holder.cv_my_teacher_pic);
            getTeacher_picTask.execute();
//            holder.cv_teacher_country.setText(myTeacherCardVO.getMem_country());
            holder.cv_my_teacher_language.setText(myTeacherCardVO.getLanguage());
//            holder.cv_teacher_price.setText(String.valueOf(myTeacherCardVO.getCourse_price()));
            holder.cv_my_teacher_nick.setText(myTeacherCardVO.getMem_nick());
            holder.cv_my_teacher_remain_hour.setText(String.valueOf(myTeacherCardVO.getRemain_hour()));

            holder.cv_my_teacher_ma_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //sending values to the bottomfragment
                    if(!(holder.cv_my_teacher_remain_hour.getText().toString().equals("0"))) {
                    HomeFragmentTabMyTeacherBottomFragment bottomFragment = new HomeFragmentTabMyTeacherBottomFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("teacher_id", myTeacherCardVO.getTeacher_id());
                    bundle.putString("member_id", preferences.getString("account", ""));
                    bundle.putString("teacher_name", myTeacherCardVO.getMem_name());
                    bottomFragment.setArguments(bundle);

                    BottomSheetDialogFragment bottomSheetDialogFragment = bottomFragment;
                    bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());
                    } else {
                        Util.showToast(getActivity(), "時數不足!");
                    }
                }
            });


            // itemView為ViewHolder內建屬性(指的就是每一列)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(getContext(), TeacherDetailActivity.class);
                        Bundle bundle = getActivity().getIntent().getExtras();
                        bundle.putSerializable("myTeacherCardVO", myTeacherCardVO);
                        intent.putExtras(bundle);
                        startActivity(intent);
                }
            });
        }
    }

}
