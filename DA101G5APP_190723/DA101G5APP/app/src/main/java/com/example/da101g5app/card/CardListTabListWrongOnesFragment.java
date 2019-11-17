package com.example.da101g5app.card;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.CommonTask;
import com.example.da101g5app.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CardListTabListWrongOnesFragment extends Fragment {

    private static final String TAG = "cardTab2";

    private SwipeRefreshLayout swipeRefreshLayout;
    private CommonTask getCardVOAppTask;
    private FloatingActionButton card_list_all_tab_fab_choice;
    private FloatingActionButton card_list_all_tab_fab_voice;
    private FloatingActionButton card_list_all_tab_fab_az;
    private boolean isFABOpen;
    private String member_card_group_id;
    private String jsonIn;

    CardListTabListWrongOnesFragment.CardVOAppAdapter cardVOAppAdapter;
    List<CardVOApp> cardVOAppList = null;
    List<CardVOApp> filterResultList = null;
    TextToSpeech tts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_tab_list_all, container, false);


        swipeRefreshLayout = view.findViewById(R.id.card_list_all_tab_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<CardVOApp> newList = null;
                JsonObject jsonObject = new JsonObject();
                Intent intent = getActivity().getIntent();
                member_card_group_id = intent.getStringExtra("member_card_group_id");
                jsonObject.addProperty("member_card_group_id", member_card_group_id);
                jsonObject.addProperty("action", "getAllByMCG_Id");
                String jsonOut = jsonObject.toString();

                getCardVOAppTask = new CommonTask(Util.URL + "CardServletApp", jsonOut);
//        List<CardVOApp> cardVOAppList = null;
                try {
                    jsonIn = getCardVOAppTask.execute().get();
                    Type listType = new TypeToken<List<CardVOApp>>() {
                    }.getType();
                    newList = new Gson().fromJson(jsonIn, listType);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                if (newList != null) {
                    newList = newList.stream()
                            .filter(cvo -> (Integer.valueOf(cvo.getChoice_card_group())) > 0).collect(Collectors.toList());
                    filterResultList.clear();
                    filterResultList.addAll(newList);
                    cardVOAppAdapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.card_list_activity_rv);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        JsonObject jsonObject = new JsonObject();
        Intent intent = getActivity().getIntent();
        member_card_group_id = intent.getStringExtra("member_card_group_id");
        jsonObject.addProperty("member_card_group_id", member_card_group_id);
        jsonObject.addProperty("action", "getAllByMCG_Id");
        String jsonOut = jsonObject.toString();

        getCardVOAppTask = new CommonTask(Util.URL + "CardServletApp", jsonOut);
//        List<CardVOApp> cardVOAppList = null;
        try {
            jsonIn = getCardVOAppTask.execute().get();
            Type listType = new TypeToken<List<CardVOApp>>() {
            }.getType();
            cardVOAppList = new Gson().fromJson(jsonIn, listType);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        if (cardVOAppList == null || cardVOAppList.isEmpty()) {
            Util.showToast(getActivity(), "Card List Not Found");
        } else {

            //filtering
            filterResultList = cardVOAppList;
            filterResultList = cardVOAppList.stream()
                    .filter(cvo -> (Integer.valueOf(cvo.getChoice_card_group())) > 0).collect(Collectors.toList());

            cardVOAppAdapter = new CardListTabListWrongOnesFragment.CardVOAppAdapter(filterResultList);
            recyclerView.setAdapter(cardVOAppAdapter);
        }

        // set up tts
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });

        //set up floating buttons
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        card_list_all_tab_fab_choice = (FloatingActionButton) view.findViewById(R.id.card_list_all_tab_fab_choice);
        card_list_all_tab_fab_az = (FloatingActionButton) view.findViewById(R.id.card_list_all_tab_fab_az);
        card_list_all_tab_fab_voice = (FloatingActionButton) view.findViewById(R.id.card_list_all_tab_fab_voice);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        card_list_all_tab_fab_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CardActivityMultiChoice.class);
                intent.putExtra("member_card_group_id", member_card_group_id);
                Type listType = new TypeToken<List<CardVOApp>>() {
                }.getType();
                intent.putExtra("cardVOAppList", new Gson().toJson(filterResultList, listType));
                if (filterResultList.size() < 5) {
                    Util.showToast(getActivity(), "卡片數量需大於5!");
                } else {
                    startActivity(intent);
                }
            }
        });
        card_list_all_tab_fab_az.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!filterResultList.isEmpty()) {
                    Intent intent = new Intent(getActivity(), CardActivitySpellingTest.class);
                    intent.putExtra("member_card_group_id", member_card_group_id);
                    Type listType = new TypeToken<List<CardVOApp>>() {
                    }.getType();
                    intent.putExtra("cardVOAppList", new Gson().toJson(filterResultList, listType));
                    startActivity(intent);
                } else {
                    Util.showToast(getActivity(), "卡片數量需大於0");
                }
            }
        });
        card_list_all_tab_fab_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!filterResultList.isEmpty()) {
                    Intent intent = new Intent(getActivity(), CardActivityVoiceTest.class);
                    intent.putExtra("member_card_group_id", member_card_group_id);
                    Type listType = new TypeToken<List<CardVOApp>>() {
                    }.getType();
                    intent.putExtra("cardVOAppList", new Gson().toJson(filterResultList, listType));
                    startActivity(intent);
                } else {
                    Util.showToast(getActivity(), "卡片數量需大於0");
                }
            }
        });
        return view;
    }

    private void showFABMenu() {
        isFABOpen = true;
        //map
        card_list_all_tab_fab_choice.animate().translationY(-10);
        card_list_all_tab_fab_choice.animate().translationX(-320);
        //camera
        card_list_all_tab_fab_az.animate().translationY(-230);
        card_list_all_tab_fab_az.animate().translationX(-240);
        //voice
        card_list_all_tab_fab_voice.animate().translationY(-330);
        card_list_all_tab_fab_voice.animate().translationX(-20);
    }

    private void closeFABMenu() {
        isFABOpen = false;
        card_list_all_tab_fab_choice.animate().translationX(0);
        card_list_all_tab_fab_choice.animate().translationY(0);
        card_list_all_tab_fab_az.animate().translationX(0);
        card_list_all_tab_fab_az.animate().translationY(0);
        card_list_all_tab_fab_voice.animate().translationX(0);
        card_list_all_tab_fab_voice.animate().translationY(0);
    }

    private class CardVOAppAdapter extends RecyclerView.Adapter<CardListTabListWrongOnesFragment.CardVOAppAdapter.ViewHolder> {
        private List<CardVOApp> cardVOAppList;

        private CardVOAppAdapter(List<CardVOApp> cardVOAppList) {
            this.cardVOAppList = cardVOAppList;
        }

        //建立ViewHolder，藉由ViewHolder做元件綁定
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cv_card_word;
            private TextView cv_card_phonetic_symbol;
            private TextView cv_card_native_explain;
            private Button cv_card_voiceBtn;
            private TextView cv_card_rightNum;
            private TextView cv_card_wrongNum;
            private TextView cv_card_last_review;
            private TextView cv_card_rate;

            private ViewHolder(View view) {
                super(view);
                cv_card_word = view.findViewById(R.id.cv_card_word);
                cv_card_phonetic_symbol = view.findViewById(R.id.cv_card_phonetic_symbol);
                cv_card_native_explain = view.findViewById(R.id.cv_card_native_explain);
                cv_card_voiceBtn = view.findViewById(R.id.cv_card_voiceBtn);
                cv_card_rightNum = view.findViewById(R.id.cv_card_rightNum);
                cv_card_wrongNum = view.findViewById(R.id.cv_card_wrongNum);
                cv_card_last_review = view.findViewById(R.id.cv_card_last_review);
                cv_card_rate = view.findViewById(R.id.cv_card_rate);

            }
        }

        @Override
        public int getItemCount() {
            return cardVOAppList.size();
        }

        @Override
        public CardListTabListWrongOnesFragment.CardVOAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_card_wrong_ones, parent, false);
            return new CardListTabListWrongOnesFragment.CardVOAppAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardListTabListWrongOnesFragment.CardVOAppAdapter.ViewHolder holder, int position) {
            //將資料注入到View裡
            final CardVOApp cardVOApp = cardVOAppList.get(position);
            holder.cv_card_word.setText(cardVOApp.getWord());
            holder.cv_card_phonetic_symbol.setText(cardVOApp.getPhonetic_symbol());
            holder.cv_card_native_explain.setText(cardVOApp.getNative_explain());
            holder.cv_card_voiceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tts.speak(cardVOApp.getWord(), TextToSpeech.QUEUE_FLUSH, null);
                }
            });

            holder.cv_card_rightNum.setText(String.valueOf(cardVOApp.getRight_number()));

            //for wrong one tab
            holder.cv_card_wrongNum.setText(String.valueOf(cardVOApp.getChoice_card_group()));

            int base = (cardVOApp.getRight_number() + cardVOApp.getWrong_number());
            float rate = (Float.valueOf(cardVOApp.getRight_number()) / Float.valueOf(((base == 0) ? 1 : base))) * 100;
            holder.cv_card_rate.setText(String.valueOf((int) rate) + "%");
            holder.cv_card_last_review.setText(cardVOApp.getReview_time().toString().substring(0, 16));

            // itemView為ViewHolder內建屬性(指的就是每一列)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(getActivity(), cardVOApp.getWord(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
