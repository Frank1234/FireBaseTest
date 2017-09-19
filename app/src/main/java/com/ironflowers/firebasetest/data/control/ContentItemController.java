package com.ironflowers.firebasetest.data.control;

import android.support.annotation.Nullable;

import com.ironflowers.firebasetest.data.model.ContentItem;

import java.util.List;

/**
 * Controller for the ContentItem model.
 */
public class ContentItemController {

    /**
     * Returns the item in items with id, or null when not found.
     */
    @Nullable
    public ContentItem findItemForId(List<ContentItem> items, int id) {

        for (ContentItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
