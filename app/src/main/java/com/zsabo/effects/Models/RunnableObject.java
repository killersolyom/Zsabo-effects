package com.zsabo.effects.Models;

public class RunnableObject {
    private Runnable runnable;

    public RunnableObject(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
