package com.ironflowers.firebasetest.data.repo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    FirebaseDatabase database;
    DatabaseReference contentDbRef;

    @Inject
    FireBaseContentRepository(FirebaseDatabase database, @Named(DB_PATH_CONTENT) DatabaseReference contentDbRef) {
        this.database = database;
        this.contentDbRef = contentDbRef;
    }

    @Override
    public Single<List<ContentItem>> getContentItems() {

        return Single.create(singleSubscriber ->

                contentDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        List<ContentItem> contentItems = new ArrayList<>((int) dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            contentItems.add(postSnapshot.getValue(ContentItem.class));
                        }
                        contentDbRef.removeEventListener(this);

                        singleSubscriber.onSuccess(contentItems);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Timber.w("onCancelled " + databaseError);
                        contentDbRef.removeEventListener(this);

                        if (!singleSubscriber.isDisposed()) {
                            singleSubscriber.onError(databaseError.toException());
                        }
                    }
                }));
    }

    @Override
    public Single<ContentItem> getContentItem(int id) {

        return Single.create(singleSubscriber ->

                contentDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contentDbRef.removeEventListener(this);
                        singleSubscriber.onSuccess(dataSnapshot.child(
                                Integer.toString(id)).getValue(ContentItem.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Timber.w("onCancelled " + databaseError);
                        contentDbRef.removeEventListener(this);

                        if (!singleSubscriber.isDisposed()) {
                            singleSubscriber.onError(databaseError.toException());
                        }
                    }
                }));
    }
}