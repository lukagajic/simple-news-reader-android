package com.lgajic.primerrss.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsItem {
    private String title;
    private Date publishedAt;
    private String link;
    private String thumbnail;
    private String description;


    public static List<NewsItem> parseJSONArray(JSONObject object) {
        List<NewsItem> items = new ArrayList<>();

        JSONArray array = new JSONArray();

        try {
            array = object.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < array.length(); i++) {
                NewsItem item =  parseJSONObject(array.getJSONObject(i));
                items.add(item);
            }
        } catch (Exception e) {
            // ...
        }
        return items;
    }

    public static NewsItem parseJSONObject(JSONObject object) {
        NewsItem item = new NewsItem();

        try {
            item.setTitle(object.getString("title"));
            item.setDescription(object.getString("description"));
            item.setLink(object.getString("link"));
            item.setThumbnail(object.getString("thumbnail"));
            item.setPublishedAt(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(object.getString("pubDate")));
        } catch (Exception e) {
            // ...
        }

        return item;
    }


    public NewsItem() {
    }

    public NewsItem(String title, Date publishedAt, String link, String thumbnail, String description) {
        this.title = title;
        this.publishedAt = publishedAt;
        this.link = link;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", publishedAt=" + publishedAt +
                ", link='" + link + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
