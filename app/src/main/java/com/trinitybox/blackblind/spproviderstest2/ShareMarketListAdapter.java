package com.trinitybox.blackblind.spproviderstest2;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ShareMarketListAdapter extends RecyclerView.Adapter<ShareMarketListAdapter.ViewHolder> {


    private List<ShareMarket> shareMarketList;
    ShareMarketListAdapter(List<ShareMarket> shareMarketList){

        this.shareMarketList = shareMarketList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_market,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.quoteText.setText(shareMarketList.get(position).getQuote());
//        holder.authorText.setText(shareMarketList.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return shareMarketList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View mView;

        TextView quoteText;
//        TextView authorText;



        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;


            quoteText = mView.findViewById(R.id.tv_quote);
//            authorText = mView.findViewById(R.id.tv_author);



        }
    }
}