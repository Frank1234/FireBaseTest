package com.ironflowers.firebasetest.data.repo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ironflowers.firebasetest.data.model.ContentItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Loads {@link ContentItem} objects from the database.
 */
@Singleton
public class FireBaseContentRepository implements ContentRepository {

    private DatabaseReference contentDatabaseReference;

    @Inject
    FireBaseContentRepository(@Named(DB_PATH_CONTENT) DatabaseReference contentDatabaseReference) {
        this.contentDatabaseReference = contentDatabaseReference;
    }

    @Override
    public Single<List<ContentItem>> getContentItems() {

        return Single.create(singleSubscriber ->

                contentDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        List<ContentItem> contentItems = new ArrayList<>((int) dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            contentItems.add(postSnapshot.getValue(ContentItem.class));
                        }
                        contentDatabaseReference.removeEventListener(this);

                        singleSubscriber.onSuccess(contentItems);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Timber.w("onCancelled " + databaseError);
                        contentDatabaseReference.removeEventListener(this);

                        if (!singleSubscriber.isDisposed()) {
                            singleSubscriber.onError(databaseError.toException());
                        }
                    }
                }));
    }

    @Override
    public Single<ContentItem> getContentItem(int id) {

        return Single.create(singleSubscriber ->

                contentDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contentDatabaseReference.removeEventListener(this);
                        singleSubscriber.onSuccess(dataSnapshot.child(
                                Integer.toString(id)).getValue(ContentItem.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Timber.w("onCancelled " + databaseError);
                        contentDatabaseReference.removeEventListener(this);

                        if (!singleSubscriber.isDisposed()) {
                            singleSubscriber.onError(databaseError.toException());
                        }
                    }
                }));
    }
}