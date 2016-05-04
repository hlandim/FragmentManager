package com.hlandim.fragmentmanager;

/**
 * Created by hlandim on 5/2/16.
 */
public class NavigationException extends Exception {
    public NavigationException() {
    }

    public NavigationException(String detailMessage) {
        super(detailMessage);
    }
}