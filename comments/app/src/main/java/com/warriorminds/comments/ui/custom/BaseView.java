package com.warriorminds.comments.ui.custom;

public interface BaseView<T> {

    void setPresenter(T presenter);
    void showError(String error);

}