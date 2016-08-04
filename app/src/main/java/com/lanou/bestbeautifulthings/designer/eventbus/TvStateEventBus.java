package com.lanou.bestbeautifulthings.designer.eventbus;

/**
 * Created by dllo on 16/8/4.
 */
public class TvStateEventBus {
    int stateChecked = 0;
    int state = 1;


    public TvStateEventBus(int stateChecked, int state) {
        this.stateChecked = stateChecked;
        this.state = state;
    }

    public int getStateChecked() {
        return stateChecked;
    }

    public void setStateChecked(int stateChecked) {
        this.stateChecked = stateChecked;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
