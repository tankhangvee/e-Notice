package com.example.e_notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoticeAdapter extends ArrayAdapter {

    private ArrayList<NoticeModel> data;
    private Context mContext;

    public NoticeAdapter(Context context, ArrayList<NoticeModel> data){
        super(context, R.layout.singlenoticelistitem);
        this.data = data;
        this.mContext = context;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlenoticelistitem,null);
        ViewHolder holder = new ViewHolder();;
        if(convertView == null){
            holder.tvTitle = v.findViewById(R.id.tv_title);
            holder.tvDate = v.findViewById(R.id.tv_date);

            v.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            //v = convertView;
        }
        NoticeModel model = (NoticeModel) getItem(position);
        holder.tvTitle.setText(model.getTitle());
        holder.tvDate.setText(model.getDate());

        return v;
    }

    class ViewHolder{
        TextView tvTitle,tvDate;
    }
}
