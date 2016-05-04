package com.hlandim.fragmentmanager.manager;

import com.hlandim.fragmentmanager.R;
import com.hlandim.fragmentmanager.fragment.BaseFragment;

/**
 * Created by hlandim on 4/7/16.
 */
public class TransitionPageConfig {

    private PageId nextPageId;
    private boolean removeCurrent;
    private boolean replaceNext;
    private Animation animation;

    public TransitionPageConfig(PageId nextPageId, Animation animation) {
        this.nextPageId = nextPageId;
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    public BaseFragment getFragment() {
        return nextPageId.getFragment();
    }

    public boolean isRemoveCurrent() {
        return removeCurrent;
    }

    public void setRemoveCurrent(boolean removeCurrent) {
        this.removeCurrent = removeCurrent;
    }

    public boolean isReplaceNext() {
        return replaceNext;
    }

    public void setReplaceNext(boolean replaceNext) {
        this.replaceNext = replaceNext;
    }

    public int getFragment_container() {
        return nextPageId.getContainerId();
    }

    public enum Animation {

        SLIDE_LEFT_TO_RIGHT(R.animator.slide_in_right, R.animator.slide_out_right),
        SLIDE_RIGHT_TO_LEFT(R.animator.slide_in_left, R.animator.slide_out_left),
        SLIDE_BOTTOM_TO_UP(R.animator.slide_in_bottom, R.animator.slide_out_top),
        STATIONARY(R.animator.stationary, R.animator.stationary);

        private int inTransition;
        private int outTransition;

        Animation(int inTransition, int outTransition) {
            this.inTransition = inTransition;
            this.outTransition = outTransition;
        }

        public int getInTransition() {
            return inTransition;
        }

        public int getOutTransition() {
            return outTransition;
        }
    }

}
