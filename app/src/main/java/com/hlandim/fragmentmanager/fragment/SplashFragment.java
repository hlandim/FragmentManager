package com.hlandim.fragmentmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hlandim.fragmentmanager.R;
import com.hlandim.fragmentmanager.manager.PageId;
import com.hlandim.fragmentmanager.manager.PageManager;
import com.hlandim.fragmentmanager.manager.Utility;

/**
 * Created by hlandim on 4/7/16.
 */
public class SplashFragment extends BaseFragment {

    private Button btn_nextPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_base, container, false);
        init(mainView, PageId.SPLASH, PageId.LOGIN, PageId.SIGN_UP);
        return mainView;
    }

//    @Override
//    public void configureBtnNextValidPage(PageId correcPage) {
//        btn_nextPage = (Button) mainView.findViewById(R.id.btn_nextPage);
//        btn_nextPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Utility.isLogged(getActivity())) {
//                    PageManager.getInstance().goToPage(PageId.HOME);
//                } else {
//                    PageManager.getInstance().goToPage(PageId.LOGIN);
//                }
//            }
//        });
//    }
}
