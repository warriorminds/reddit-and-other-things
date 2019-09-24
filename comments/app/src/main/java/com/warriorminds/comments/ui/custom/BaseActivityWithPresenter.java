package com.warriorminds.comments.ui.custom;

import android.widget.Toast;

public class BaseActivityWithPresenter<T extends BasePresenter> extends BaseActivity implements BaseView<T> {

    protected T presenter;

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(String error) {
        //TODO: Show error dialog.
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

}
