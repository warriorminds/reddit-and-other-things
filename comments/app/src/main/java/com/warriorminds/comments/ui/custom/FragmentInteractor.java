package com.warriorminds.comments.ui.custom;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

public interface FragmentInteractor {

    void finish();

    void setTitle(String title);

    void replaceFragment(Fragment fragment, boolean addToBackStack);

    void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack);

    void showError(String message);

    void setToolbar(Toolbar toolbar);

}
