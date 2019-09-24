package com.warriorminds.comments.ui.home;

import com.warriorminds.comments.adapter.base.DisplayableItem;
import com.warriorminds.comments.ui.custom.BasePresenter;
import com.warriorminds.comments.ui.custom.BaseView;

import java.util.List;

public interface HomeContractor {

    interface View extends BaseView<Presenter> {

        void addedItem();

        void modifiedItem(int position);

        void enableScrollListener();

        void initView(List<DisplayableItem> displayableItems);

        void showPost(String permalink);
    }

    interface Presenter extends BasePresenter {

        void loadMore();

        void onPostClicked(int position);

        void onFavoriteClicked(int position);
    }

}
