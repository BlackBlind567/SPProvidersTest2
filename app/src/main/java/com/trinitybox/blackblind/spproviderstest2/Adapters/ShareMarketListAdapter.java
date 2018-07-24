package com.trinitybox.blackblind.spproviderstest2.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trinitybox.blackblind.spproviderstest2.Objects.ShareMarket;
import com.trinitybox.blackblind.spproviderstest2.R;

import java.util.Calendar;
import java.util.List;

public class ShareMarketListAdapter extends RecyclerView.Adapter<ShareMarketListAdapter.ViewHolder> {

    private List<ShareMarket> shareMarketList;

    public ShareMarketListAdapter(List<ShareMarket> shareMarketList) {
        this.shareMarketList = shareMarketList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_market, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Generate Current Date & Time
//        Calendar calendar = Calendar.getInstance();
//        String date = String.valueOf(calendar.get(Calendar.DATE));
//        String month = String.valueOf(calendar.get(Calendar.MONTH));
//        String year = String.valueOf(calendar.get(Calendar.YEAR));
//        String dateString = date + "/" + month + "/" + year;
//        String timeString = String.valueOf(calendar.get(Calendar.HOUR) + ":" +
//                String.valueOf(calendar.get(Calendar.MINUTE)));

        holder.quoteText.setText(shareMarketList.get(position).getQuote());
        holder.dateText.setText(shareMarketList.get(position).getDateValue());
        holder.timeText.setText(shareMarketList.get(position).getTimeValue());
    }

    @Override
    public int getItemCount() {
        return shareMarketList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView quoteText, dateText, timeText;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            quoteText = mView.findViewById(R.id.tv_quote);
            dateText = mView.findViewById(R.id.tv_date);
            timeText = mView.findViewById(R.id.tv_time);
        }
    }
}