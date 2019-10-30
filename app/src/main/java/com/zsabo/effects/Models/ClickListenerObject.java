package com.zsabo.effects.Models;

import android.view.View;

public class ClickListenerObject {
    private View.OnClickListener clickListener;

    public ClickListenerObject(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }
}
