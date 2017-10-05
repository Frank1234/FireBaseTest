package com.ironflowers.firebasetest.ui.content;

import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.data.model.ContentItem;
import com.ironflowers.firebasetest.data.repo.FireBaseContentRepository;
import com.ironflowers.firebasetest.ui.content.vm.ContentViewModel;
import com.ironflowers.firebasetest.ui.home.HomePresenter;
import com.ironflowers.firebasetest.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link HomePresenter}
 */
public class ContentPresenterTest {

    @Mock
    private FireBaseContentRepository contentRepository;
    @Mock
    private ContentContract.View contentView;
    @Mock
    private StorageReference storageReference;
    @Mock
    private ContentItem contentItem;

    private ContentViewModel viewModel;

    private ContentPresenter contentPresenter;
    private TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        int itemId = 2;
        viewModel = new ContentViewModel();
        testScheduler = new TestScheduler();
        contentPresenter = new ContentPresenter(contentRepository,
                new TestSchedulerProvider(testScheduler),
                itemId, viewModel);

        // mock the Single:
        when(contentRepository.getContentItem(itemId)).thenReturn(Single.just(contentItem));

        contentPresenter.attachView(contentView);

        // trigger the initial load:
        testScheduler.triggerActions();

        // The presenter wont't update the view unless it's active:
        when(contentView.isActive()).thenReturn(true);
    }

    @Test
    public void loadEmptyContentsFromRepository_UpdateViewModel() {

        assertTrue(viewModel.isShowLoadingIndicator());
        assertFalse(viewModel.isShowErrorMessage());

        contentPresenter.loadContent();
        testScheduler.triggerActions();

        // Progress indicator is hidden and empty data is passed on to the view
        assertFalse(viewModel.isShowLoadingIndicator());
        assertFalse(viewModel.isShowErrorMessage());
        assertNull(viewModel.getContentDescription());
        assertNull(viewModel.getImageUrl());
    }

    @Test
    public void loadingErrorAtRepository_UpdateViewModelToDisplayError() {

        when(contentRepository.getContentItem(anyInt())).thenReturn(Single.error(new RuntimeException()));

        assertTrue(viewModel.isShowLoadingIndicator());
        assertFalse(viewModel.isShowErrorMessage());

        contentPresenter.loadContent();
        testScheduler.triggerActions();

        assertFalse(viewModel.isShowLoadingIndicator());
        assertTrue(viewModel.isShowErrorMessage());
        assertNull(viewModel.getContentDescription());
        assertNull(viewModel.getImageUrl());
    }

    @Test
    public void assertViewActive_True() {
        assertTrue(contentPresenter.isViewActive(contentView));
    }

    @Test
    public void assertViewActive_False() {
        when(contentView.isActive()).thenReturn(false);
        assertFalse(contentPresenter.isViewActive(contentView));
    }

    @Test
    public void assertViewActive_Null() {
        assertFalse(contentPresenter.isViewActive(null));
    }
}