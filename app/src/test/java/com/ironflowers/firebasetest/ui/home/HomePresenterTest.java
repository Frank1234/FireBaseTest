package com.ironflowers.firebasetest.ui.home;

import com.ironflowers.firebasetest.data.repo.FireBaseContentRepository;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModel;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModelFactory;
import com.ironflowers.firebasetest.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link HomePresenter}
 */
public class HomePresenterTest {

    @Mock
    private FireBaseContentRepository contentRepository;
    @Mock
    private HomeContract.View homeView;

    private HomePresenter homePresenter;
    private TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();
        homePresenter = new HomePresenter(contentRepository,
                new TestSchedulerProvider(testScheduler),
                new HomeItemViewModelFactory());

        // mock the Single:
        when(contentRepository.getContentItems()).thenReturn(Single.just(Collections.emptyList()));

        homePresenter.attachView(homeView);

        // trigger the initial load:
        testScheduler.triggerActions();

        // The presenter wont't update the view unless it's active:
        when(homeView.isActive()).thenReturn(true);
    }

    @Test
    public void loadEmptyListFromRepository_CallViewToDisplay() {

        verify(homeView).showProgressIndicator(true);

        homePresenter.loadItems();
        testScheduler.triggerActions();

        // Progress indicator is hidden and correct data is passed on to the view
        verify(homeView).showProgressIndicator(false);
        verify(homeView).setContentItems(anyList());
    }

    @Test
    public void loadingErrorFromRepository_CallViewToDisplayError() {

        when(contentRepository.getContentItems()).thenReturn(Single.error(new RuntimeException()));

        verify(homeView).showProgressIndicator(true);

        homePresenter.loadItems();
        testScheduler.triggerActions();

        verify(homeView).showProgressIndicator(false);
        verify(homeView).showLoadingError(true);
        verify(homeView, never()).setContentItems(anyList());
    }

    @Test
    public void clickingOfHomeItem_CallViewToOpenContentPage() {

        homePresenter.onHomeItemClicked(new HomeItemViewModel(2));
        verify(homeView).openContentPage(2, null);
    }

    @Test
    public void assertViewActive_True() {
        assertTrue(homePresenter.isViewActive(homeView));
    }

    @Test
    public void assertViewActive_False() {
        when(homeView.isActive()).thenReturn(false);
        assertFalse(homePresenter.isViewActive(homeView));
    }

    @Test
    public void assertViewActive_Null() {
        assertFalse(homePresenter.isViewActive(null));
    }
}