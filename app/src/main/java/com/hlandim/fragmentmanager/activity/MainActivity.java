package com.hlandim.fragmentmanager.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.hlandim.fragmentmanager.R;
import com.hlandim.fragmentmanager.manager.PageId;
import com.hlandim.fragmentmanager.manager.PageManager;

public class MainActivity extends AppCompatActivity {

    private static final String LAST_PAGE_KEY = "lastPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageManager pageManager = PageManager.getInstance();
        pageManager.setActivity(this);

        PageId pageId = PageId.SPLASH;
        if (savedInstanceState != null) {
            String lastPage = savedInstanceState.getString(LAST_PAGE_KEY, null);
            if (!TextUtils.isEmpty(lastPage)) {
                pageId = PageId.valueOf(lastPage);
            }
        }
        pageManager.goToPage(pageId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(LAST_PAGE_KEY, PageManager.getInstance().getCurrentPage().name());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onDestroy() {
        PageManager.getInstance().close();
        super.onDestroy();
    }
}
