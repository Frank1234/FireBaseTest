package com.ironflowers.firebasetest.data.repo;

import android.support.annotation.Nullable;

import com.ironflowers.firebasetest.data.control.ContentItemController;
import com.ironflowers.firebasetest.data.model.ContentItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Fake content repository connector, for easy espresso testing.
 */
public class FakeContentRepository implements ContentRepository {

    @Inject
    ContentItemController contentItemController;

    /**
     * Data to fake as repository content, or null to always fake failures.
     */
    @Nullable
    private static List<ContentItem> fakeData = null;

    @Inject
    FakeContentRepository() {
    }

    /**
     * Data to fake as repository content, or null to always fake failures.
     */
    public static void setFakeData(@Nullable List<ContentItem> fakeData) {
        FakeContentRepository.fakeData = fakeData;
    }

    @Override
    public Single<List<ContentItem>> getContentItems() {

        if (fakeData != null) {
            return Single.just(fakeData);
        } else {
            return Single.error(new Exception("My mocked/fake error"));
        }
    }

    @Override
    public Single<ContentItem> getContentItem(int id) {

        if (fakeData != null) {

            ContentItem contentItem = contentItemController.
                    findItemForId(fakeData, id);

            if (contentItem != null) {
                return Single.just(contentItem);
            } else {
                return Single.error(new Exception("ContentItem not found"));
            }

        } else {
            return Single.error(new Exception("My mocked/fake error"));
        }
    }
}