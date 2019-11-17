package com.example.da101g5app.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.da101g5app.R;

import java.util.List;

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.ViewHolder> {
    private Context context;
    private int imageSize;
    private LayoutInflater layoutInflater;

    public EmptyAdapter() {
        super();
    }
    public EmptyAdapter(List<Object> list) {

    }

    //建立ViewHolder，藉由ViewHolder做元件綁定
    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewHolder(View view) {
            super(view);

        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_norecords, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //將資料注入到View裡
    }
}

