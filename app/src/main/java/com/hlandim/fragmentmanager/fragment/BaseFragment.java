package com.hlandim.fragmentmanager.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hlandim.fragmentmanager.R;
import com.hlandim.fragmentmanager.fragment.interfaces.FragmentStateListener;
import com.hlandim.fragmentmanager.manager.PageId;
import com.hlandim.fragmentmanager.manager.PageManager;

/**
 * Created by hlandim on 4/7/16.
 */
public class BaseFragment extends Fragment {

    private FragmentStateListener mFragmentStateListener;
    private Button btn_nextPage;
    private Button btn_nextInvalidPage;
    private TextView tv_name;
    private TextView tv_container_name;
    public View mainView;

    public void init(View mainView, PageId actualPage, PageId nextPage, PageId wrongPage) {
        this.mainView = mainView;
        configureTitle(actualPage);
        configureContainerName(actualPage);
        configureBtnNextValidPage(nextPage);
        configureBtnInvalidPage(wrongPage);
    }

    private void configureContainerName(PageId actualPage){
        tv_container_name = (TextView) mainView.findViewById(R.id.tv_container_name);
        String container;
        if(actualPage.getContainerId() == R.id.fragment_container_1){
            container = "fragment_container_1";
        } else {
            container = "fragment_container_2";
        }
        tv_container_name.setText(container);
    }

    private void configureTitle(PageId actualPage) {
        tv_name = (TextView) mainView.findViewById(R.id.tv_name);
        tv_name.setText(actualPage.name());
    }

    public void configureBtnNextValidPage(final PageId correcPage) {

        btn_nextPage = (Button) mainView.findViewById(R.id.btn_nextPage);
        btn_nextPage.setText(correcPage.name());
        btn_nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageManager.getInstance().goToPage(correcPage);
            }
        });
    }

    private void configureBtnInvalidPage(final PageId wrongPage) {
        btn_nextInvalidPage = (Button) mainView.findViewById(R.id.btn_nextInvalidPage);
        btn_nextInvalidPage.setText(wrongPage.name());
        btn_nextInvalidPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageManager.getInstance().goToPage(wrongPage);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFragmentStateListener != null) {
            mFragmentStateListener.onFragmentRemoved(this);
        }
    }

    public void setFragmentStateListener(FragmentStateListener fragmentStateListener) {
        this.mFragmentStateListener = fragmentStateListener;
    }

    public void addValidButton(final PageId pageId){
        Button button = new Button(getContext());
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText(pageId.name());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageManager.getInstance().goToPage(pageId);
            }
        });
        ((LinearLayout) mainView).addView(button);
    }
}
