package com.hlandim.fragmentmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hlandim.fragmentmanager.R;
import com.hlandim.fragmentmanager.manager.PageId;

/**
 * Created by hlandim on 4/7/16.
 */
public class LoginFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_base, container, false);
        init(mainView, PageId.LOGIN, PageId.HOME, PageId.SETTINGS);
        addValidButton(PageId.SIGN_UP);
        return mainView;
    }
}
