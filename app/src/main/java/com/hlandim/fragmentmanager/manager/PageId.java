package com.hlandim.fragmentmanager.manager;

import android.support.v4.app.Fragment;

import com.hlandim.fragmentmanager.NavigationException;
import com.hlandim.fragmentmanager.R;
import com.hlandim.fragmentmanager.fragment.BaseFragment;
import com.hlandim.fragmentmanager.fragment.HomeFragment;
import com.hlandim.fragmentmanager.fragment.LoginFragment;
import com.hlandim.fragmentmanager.fragment.SettingsFragment;
import com.hlandim.fragmentmanager.fragment.SignUpFragment;
import com.hlandim.fragmentmanager.fragment.SplashFragment;
import com.hlandim.fragmentmanager.fragment.UserSettingsFragment;

/**
 * Created by hlandim on 4/5/16.
 */
public enum PageId {

    SPLASH(R.id.fragment_container_1) {
        @Override
        BaseFragment getFragmentInstance() {
            return new SplashFragment();
        }

        @Override
        public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
            switch (page) {
                case SPLASH:
                case LOGIN:
                case HOME:
                    return new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_LEFT_TO_RIGHT);
            }
            return super.getNextPage(page);
        }
    },
    LOGIN(R.id.fragment_container_1) {
        @Override
        BaseFragment getFragmentInstance() {
            return new LoginFragment();
        }

        @Override
        public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
            switch (page) {
                case SIGN_UP:
                case HOME:
                    return new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_RIGHT_TO_LEFT);
            }
            return super.getNextPage(page);
        }
    },
    SIGN_UP(R.id.fragment_container_2) {
        @Override
        BaseFragment getFragmentInstance() {
            return new SignUpFragment();
        }

        @Override
        public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
            TransitionPageConfig transitionPageConfig = new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_RIGHT_TO_LEFT);
            switch (page) {
                case LOGIN:
                    transitionPageConfig.setRemoveCurrent(true);
                    return transitionPageConfig;
                case HOME:
                    transitionPageConfig.setRemoveCurrent(true);
                    transitionPageConfig.setReplaceNext(true);
                    return transitionPageConfig;
            }
            return super.getNextPage(page);
        }
    },
    HOME(R.id.fragment_container_1) {
        @Override
        BaseFragment getFragmentInstance() {
            return new HomeFragment();
        }

        @Override
        public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
            switch (page) {
                case SETTINGS:
                    return new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_LEFT_TO_RIGHT);
                case LOGIN:
                    return new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_BOTTOM_TO_UP);
            }
            return super.getNextPage(page);
        }
    },
    SETTINGS(R.id.fragment_container_2) {
        @Override
        BaseFragment getFragmentInstance() {
            return new SettingsFragment();
        }

        @Override
        public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
            switch (page) {
                case USER_CONFIG:
                    return new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_LEFT_TO_RIGHT);
                case HOME:
                    TransitionPageConfig transitionPageConfig = new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_RIGHT_TO_LEFT);
                    transitionPageConfig.setRemoveCurrent(true);
                    return transitionPageConfig;
            }
            return super.getNextPage(page);
        }
    },
    USER_CONFIG(R.id.fragment_container_2) {
        @Override
        BaseFragment getFragmentInstance() {
            return new UserSettingsFragment();
        }

        @Override
        public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
            switch (page) {
                case SETTINGS:
                    return new TransitionPageConfig(page, TransitionPageConfig.Animation.SLIDE_RIGHT_TO_LEFT);
            }
            return super.getNextPage(page);
        }
    };


    private BaseFragment fragment;
    private int containerId;

    PageId(int containerId) {
        this.containerId = containerId;
    }

    public static PageId getByFragment(Fragment fragment) {
        for (PageId pageId : PageId.values()) {
            if (pageId.getFragment() == fragment) {
                return pageId;
            }
        }
        return null;
    }

    public BaseFragment getFragment() {
        if (fragment == null) {
            fragment = getFragmentInstance();
        }
        return fragment;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public TransitionPageConfig getNextPage(PageId page) throws NavigationException {
        throw new NavigationException("You can't navigate from " + this.name() + " to " + page);
    }

    abstract BaseFragment getFragmentInstance();

    public int getContainerId() {
        return containerId;
    }


}
