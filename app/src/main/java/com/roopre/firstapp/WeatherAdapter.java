package com.roopre.firstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter {

    // 데이터 리스트
    ArrayList<Weather> dataList = new ArrayList<>();

    // 날씨 아이콘을 처리하기 위한 HashMap
    private Map<String, Integer> mWeatherImageMap;

    // 생성
    public WeatherAdapter(ArrayList<Weather> list){
        this.dataList = list;

        mWeatherImageMap = new HashMap<>();
        mWeatherImageMap.put("맑음", R.drawable.ic_sunny);
        mWeatherImageMap.put("흐림", R.drawable.ic_cloudy);
        mWeatherImageMap.put("눈", R.drawable.ic_snow);
        mWeatherImageMap.put("비", R.drawable.ic_rain);
    }

    // ViewHolder 레이아웃에 연결
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_weather, parent,false);
        WeatherAdapter.CustomViewHolder cholder = new WeatherAdapter.CustomViewHolder(view);
        return cholder;
    }


    // ViewHolder 에 데이터 연결하기
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        CustomViewHolder viewHolder = (CustomViewHolder) holder;

        viewHolder.imageview.setImageResource(mWeatherImageMap.get(dataList.get(position).getWeather()));
        viewHolder.city_tv.setText(dataList.get(position).getCity());
        viewHolder.temp_tv.setText(dataList.get(position).getTemp());
        viewHolder.city_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("WeatherAdapter", "clicked "+dataList.get(position).getCity());
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 사용할 ViewHolder 커스텀 하는 부분
    private class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView city_tv, temp_tv;

        CustomViewHolder (View itemView){
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);
            city_tv = itemView.findViewById(R.id.city_tv);
            temp_tv = itemView.findViewById(R.id.temp_tv);
        }

    }
}
