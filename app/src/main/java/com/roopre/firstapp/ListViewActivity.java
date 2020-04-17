package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {


    // RecyclerView 선언
    RecyclerView rv;

    // RecyclerView 용 Adapter 선언
    WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // RecyclerView 초기화
        rv = findViewById(R.id.rv);

        // 데이터 리스트 삽입
        ArrayList<Weather> data = new ArrayList<>();
        data.add(new Weather("서울", "21도", "맑음"));
        data.add(new Weather("대전", "19도", "비"));
        data.add(new Weather("대구", "17도", "흐림"));
        data.add(new Weather("부산", "20도", "맑음"));
        data.add(new Weather("광주", "21도", "눈"));
        data.add(new Weather("인천", "24도", "맑음"));
        data.add(new Weather("용인", "18도", "흐림"));
        data.add(new Weather("세종", "21도", "눈"));
        data.add(new Weather("전주", "22도", "흐림"));
        data.add(new Weather("제주도", "24도", "비"));
        data.add(new Weather("춘천", "17도", "맑음"));
        data.add(new Weather("여수", "20도", "눈"));

        // Adapter 초기화
        WeatherAdapter mAdapter = new WeatherAdapter(data);

        // RecyclerView 의 레이아웃 방향을 잡아주는 LinearLayoutManager 셋팅
        rv.setLayoutManager(new LinearLayoutManager(this)) ;

        // RecyclerView 에 Adapter 연결
        rv.setAdapter(mAdapter);
    }
}
