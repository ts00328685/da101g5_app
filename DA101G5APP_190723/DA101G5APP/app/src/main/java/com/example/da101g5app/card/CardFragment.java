package com.example.da101g5app.card;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.da101g5app.login.LoginActivity.PREF_NAME;

public class CardFragment extends Fragment {
    private CommonTask getMember_card_groupVOTask;
    private List<String> MCG_IdList  = new ArrayList<String>();
    private static final String TAG = "CardFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_card_rv);
        //設定每個List是否為固定尺寸
        recyclerView.setHasFixedSize(true);

        /*
         *  透過LayoutManager的設定可以輕鬆變化RecyclerView的呈現
         *  以下分別使用LinerLayoutManager與StaggeredGridLayoutManager示範
         */

        //產生一個LinearLayoutManger
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //設定LayoutManager
        recyclerView.setLayoutManager(layoutManager);

//        recyclerView.setLayoutManager(
//                new StaggeredGridLayoutManager(
//                        2, StaggeredGridLayoutManager.HORIZONTAL));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getAllByMemberId");
        SharedPreferences preferences = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        jsonObject.addProperty("member_id", preferences.getString("account",""));

        String jsonOut = jsonObject.toString();

        getMember_card_groupVOTask = new CommonTask(Util.URL + "Member_card_gorupServletApp", jsonOut);
        List<Member_card_groupVO> member_card_groupVOList = null;
        try {
            String jsonIn = getMember_card_groupVOTask.execute().get();
            Type listType = new TypeToken<List<Member_card_groupVO>>() {
            }.getType();
            member_card_groupVOList = new Gson().fromJson(jsonIn, listType);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        if (member_card_groupVOList == null || member_card_groupVOList.isEmpty()) {
            Util.showToast(getActivity(), "MCGVO List Not Found");
        } else {
            recyclerView.setAdapter(new Member_card_groupVOAdapter(member_card_groupVOList));
        }

        return view;
    }


    private class Member_card_groupVOAdapter extends RecyclerView.Adapter<Member_card_groupVOAdapter.ViewHolder> {
        private List<Member_card_groupVO> member_card_groupVOList;

        private Member_card_groupVOAdapter(List<Member_card_groupVO> member_card_groupVOList) {
            this.member_card_groupVOList = member_card_groupVOList;
        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cv_mcg_name;
            private TextView cv_mcg_teacher;
            private TextView cv_mcg_word_qty;
            private TextView cv_mcg_last_review;


            private ViewHolder(View view) {
                super(view);
                cv_mcg_name = view.findViewById(R.id.cv_mcg_name);
                cv_mcg_teacher = view.findViewById(R.id.cv_mcg_teacher);
                cv_mcg_word_qty = view.findViewById(R.id.cv_mcg_word_qty);
                cv_mcg_last_review = view.findViewById(R.id.cv_mcg_last_review);
            }
        }

        @Override
        public int getItemCount() {
            return member_card_groupVOList.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_member_card_group, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //將資料注入到View裡
            final Member_card_groupVO member_card_groupVO = member_card_groupVOList.get(position);
            MCG_IdList.add(member_card_groupVO.getMember_card_group_id());
            holder.cv_mcg_name.setText(member_card_groupVO.getTeacher_card_group_name());
            holder.cv_mcg_teacher.setText(member_card_groupVO.getTeacher_name());
            holder.cv_mcg_word_qty.setText(String.valueOf(member_card_groupVO.getQty()));
            holder.cv_mcg_last_review.setText(member_card_groupVO.getMember_card_group_id());

            // itemView為ViewHolder內建屬性(指的就是每一列)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), CardListActivityTabbed.class);
                    String member_card_group_id = member_card_groupVO.getMember_card_group_id();
                    intent.putExtra("member_card_group_id", member_card_group_id);
                    startActivity(intent);
//                    Toast.makeText(getActivity(), Member_card_groupVO.getWord(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
