package com.lgajic.primerrss.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lgajic.primerrss.R;
import com.lgajic.primerrss.WebActivity;
import com.lgajic.primerrss.model.NewsItem;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private Context context;
    private List<NewsItem> newsItems;

    public NewsListAdapter(Context context, List<NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);

        return new NewsListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);

        Glide.with(context)
                .load(newsItem.getThumbnail())
                .into(holder.newsItemImage);

        holder.newsItemHeadline.setText(newsItem.getTitle());

        holder.newsItemButtonReadMore.setOnClickListener(view -> {
            Intent i = new Intent(context, WebActivity.class);
            i.putExtra("url", newsItem.getLink());

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }


    class NewsListViewHolder extends RecyclerView.ViewHolder {
        ImageView newsItemImage;
        TextView newsItemHeadline;
        Button newsItemButtonReadMore;

        public NewsListViewHolder(@NonNull View itemView) {
            super(itemView);

            newsItemImage = itemView.findViewById(R.id.news_item_image);
            newsItemHeadline = itemView.findViewById(R.id.news_item_headline);
            newsItemButtonReadMore = itemView.findViewById(R.id.news_item_button_read_more);
        }
    }
}
