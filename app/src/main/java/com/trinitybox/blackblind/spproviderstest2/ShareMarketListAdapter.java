package com.trinitybox.blackblind.spproviderstest2;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class ShareMarketListAdapter extends RecyclerView.Adapter<ShareMarketListAdapter.ViewHolder> {


    private List<ShareMarket> shareMarketList;

    ShareMarketListAdapter(List<ShareMarket> shareMarketList) {

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

        holder.quoteText.setText(shareMarketList.get(position).getQuote());
        Calendar calendar = Calendar.getInstance();
        String date = String.valueOf(calendar.get(Calendar.DATE));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String dateString = date + "/" + month + "/" + year;
        String timeString = String.valueOf(calendar.get(Calendar.HOUR) + ":" +
                String.valueOf(calendar.get(Calendar.MINUTE)));

        holder.dateText.setText(dateString);
        holder.timeText.setText(timeString);

//        holder.authorText.setText(shareMarketList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return shareMarketList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        //Todo I am working here
        TextView quoteText, dateText, timeText;
        //        TextView authorText;


        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            quoteText = mView.findViewById(R.id.tv_quote);
            dateText = mView.findViewById(R.id.tv_date);
            timeText = mView.findViewById(R.id.tv_time);
//            authorText = mView.findViewById(R.id.tv_author);

        }
    }
}