package com.example.ivansv.nskweather.presentation.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.data.model.Forecast;
import com.example.ivansv.nskweather.presentation.view.activity.DetailsActivity;
import com.example.ivansv.nskweather.util.Util;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {
    private Context context;
    private List<Forecast> forecastList;

    @Inject
    public ForecastListAdapter(Context context) {
        this.context = context;
        forecastList = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dateSmall.setText(forecastList.get(position).getDay().concat(context.getString(R.string.slash))
                .concat(forecastList.get(position).getMonth()));
        holder.dayTime.setText(Util.getDayTime(forecastList.get(position).getTod()));
        holder.temperature.setText(forecastList.get(position).getTemperature().getMin().concat(context.getString(R.string.slash))
        .concat(forecastList.get(position).getTemperature().getMax()));
        holder.weatherIcon.setImageResource(Util.getIcon(forecastList.get(position).getTod(),
                forecastList.get(position).getPhenomena().getCloudiness(),
                forecastList.get(position).getPhenomena().getPrecipitation(), forecastList.get(position).getPhenomena().getSpower()));
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
        @Bind(R.id.tv_date_small)
        TextView dateSmall;
        @Bind(R.id.tv_day_time)
        TextView dayTime;
        @Bind(R.id.tv_temperature)
        TextView temperature;
        @Bind(R.id.iv_weather_icon)
        ImageView weatherIcon;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(DetailsActivity.INTENT_EXTRA_PARAM_TOD, forecastList.get(getAdapterPosition()).getTod());
                    context.startActivity(intent);
                }
            });
        }
    }
}
