package com.example.e_notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.e_notice.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//http://www.objgen.com/json/models/7xOxr
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<NoticeModel> data = new ArrayList<>();
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromAPI();
    }

    private void findViews(){
        listView = findViewById(R.id.listview);

        NoticeAdapter adapter = new NoticeAdapter(this,data);
        listView.setAdapter(adapter);
    }

    private void setListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoticeModel selectedItem = (NoticeModel)listView.getAdapter().getItem(position);

                Intent i = new Intent(MainActivity.this, noticedetail.class);
                i.putExtra("title",selectedItem.getTitle());
                i.putExtra("date",selectedItem.getDate());
                i.putExtra("description",selectedItem.getDescription());

                startActivity(i);
            }
        });
    }

    private void initialize(){
        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                findViews();
                setListeners();
            }
        });
    }

    private void getDataFromAPI(){
        Request request = new Request.Builder().url("https://api.myjson.com/bins/11792e").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    JSONObject dataObject = new JSONObject(response.body().string());
                    JSONArray dataArray = dataObject.getJSONArray("data");

                    for(int i = 0 ; i < dataArray.length() ; i++){
                        JSONObject singleObject = dataArray.getJSONObject(i);

                        NoticeModel model = new NoticeModel();
                        model.setTitle(singleObject.getString("title"));
                        model.setDate(singleObject.getString("date"));
                        model.setDescription(singleObject.getString("description"));

                        data.add(model);
                    }
                    initialize();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
