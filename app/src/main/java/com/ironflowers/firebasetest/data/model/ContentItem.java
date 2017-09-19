package com.ironflowers.firebasetest.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ContentItem {

    private int id;
    private String title;
    private String description;

    /**
     * Url of the image on the storage, relative to the storage content root.
     */
    private String imageUrl;

    @SuppressWarnings("unused")
    public ContentItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Url of the image on the storage, relative to the storage content root.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}