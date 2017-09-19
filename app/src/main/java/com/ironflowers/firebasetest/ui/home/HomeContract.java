/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ironflowers.firebasetest.ui.home;

import com.ironflowers.firebasetest.BasePresenter;
import com.ironflowers.firebasetest.BaseView;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModel;

import java.util.List;

/**
 * Specifies the contract between the view and the presenter.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void showProgressIndicator(boolean show);

        void setContentItems(List<HomeItemViewModel> contentItems);

        void showLoadingError(boolean show);

        boolean isActive();

        int getItemCount();

        void openContentPage(int contentItemId, String title);
    }

    interface Presenter extends BasePresenter<View>, HomeAdapter.ItemListener {

    }
}
