package com.example.ivansv.nskweather.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.Util;
import com.example.ivansv.nskweather.model.Forecast;
import com.example.ivansv.nskweather.view.activity.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {
    private Context context;
    private List<Forecast> forecastList;

    public ForecastListAdapter(Context context, List<Forecast> forecastList) {
        this.context = context;
        this.forecastList = forecastList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dayTime.setText(Util.getDayTime(forecastList.get(position).getHour()));
        holder.temperature.setText(forecastList.get(position).getTemperature().getMin().concat(context.getString(R.string.slash))
        .concat(forecastList.get(position).getTemperature().getMax()));
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public boolean updateData(List<Forecast> forecastList) {
        if (!forecastList.equals(this.forecastList)) {
            this.forecastList = forecastList;
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_day_time)
        TextView dayTime;
        @BindView(R.id.tv_temperature)
        TextView temperature;
        @BindView(R.id.iv_weather_icon)
        ImageView weatherIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("forecast", forecastList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
