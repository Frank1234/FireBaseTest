package com.ironflowers.firebasetest.data.repo;

import com.ironflowers.firebasetest.data.model.ContentItem;

import java.util.List;

import io.reactivex.Single;

/**
 * A content repository that fetches items from the backend or cache.
 */
public interface ContentRepository {

    String DB_PATH_CONTENT = "content";

    /**
     * Retrieves content items from DB or server.
     */
    Single<List<ContentItem>> getContentItems();

    /**
     * Retrieves the content item with the given id from DB or server.
     */
    Single<ContentItem> getContentItem(int id);
}