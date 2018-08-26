package com.letter.otools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AnniAdapter extends RecyclerView.Adapter<AnniAdapter.ViewHolder> {

    private static final long MS_ONE_DAY = 86400000L;

    private Context mContext;
    private List<Anniversary> mAnniversaryList;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView anniDate;
        TextView anniDays;
        TextView anniText;
        TextView anniType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            anniDate = itemView.findViewById(R.id.anni_date);
            anniDays = itemView.findViewById(R.id.anni_days);
            anniText = itemView.findViewById(R.id.anni_text);
            anniType = itemView.findViewById(R.id.anni_type);
        }
    }

    public AnniAdapter(List<Anniversary> anniversaryList) {
        mAnniversaryList = anniversaryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.anniversary_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anniversary anniversary = mAnniversaryList.get(position);
        long time = (new Date().getTime() - anniversary.getTime()) / MS_ONE_DAY;
        holder.anniDate.setText(format.format(anniversary.getTime()));
        switch (anniversary.getType()) {
            case Anniversary.ANNI_TYPE_ONLY_ONCE:
                holder.anniDays.setText(String.valueOf(time) + "天");
                break;

            case Anniversary.ANNI_TYPE_EVERY_YEAR:
                holder.anniDays.setText(String.valueOf(time) + "天");
                break;

            case Anniversary.ANNI_TYPE_COUNT_DOWN:
                holder.anniDays.setText("余" + String.valueOf(time) + "天");
                break;

            default:
                break;
        }
        holder.anniText.setText(anniversary.getText());
        holder.anniType.setText(Anniversary.typeText[anniversary.getType()]);
    }

    @Override
    public int getItemCount() {
        return mAnniversaryList.size();
    }
}
