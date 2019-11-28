package com.example.e_notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class noticedetail extends AppCompatActivity {

    private TextView tvTitle,tvDate,tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticedetail);

        findViews();
        initializeData();
    }

    private void findViews(){
        tvTitle = findViewById(R.id.tvtitle);
        tvDate = findViewById(R.id.tvdate);
        tvDescription = findViewById(R.id.tvdescription);
    }

    private void initializeData(){
        Bundle bundle = getIntent().getExtras();
        tvTitle.setText(bundle.getString("title"));
        tvDate.setText(bundle.getString("date"));
        tvDescription.setText(bundle.getString("description"));
    }
}
