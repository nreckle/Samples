package com.nreckle.android_lifecycles;

import androidx.lifecycle.ViewModel;

public class ChronometerViewModel extends ViewModel {
    private long time = -1L;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
