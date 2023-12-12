package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WeatherRVModel> weatherRVModelArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.weather_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        WeatherRVModel model=weatherRVModelArrayList.get(position);
        holder.tempTV.setText(model.getTemp()+" °c");
        Picasso.get().load("http:".concat(model.getIcon())).into(holder.condnIV);
        holder.windTV.setText(model.getWindSpeed()+"km/h");
        SimpleDateFormat input=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output=new SimpleDateFormat("hh:mm aa");
        try {
            Date t=input.parse(model.getTime());
            holder.timeTV.setText(output.format(t));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView windTV, tempTV, timeTV;
        private ImageView condnIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTV=itemView.findViewById(R.id.idTVWindSpeed);
            tempTV=itemView.findViewById(R.id.idTVTemp);
            timeTV=itemView.findViewById(R.id.idTVTime);
            condnIV=itemView.findViewById(R.id.idTVCondn);
        }
    }
}
