package com.nreckle.android_lifecycles;

import androidx.lifecycle.ViewModel;

public class ChronometerViewModel extends ViewModel {
    private long startTime = -1L;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
