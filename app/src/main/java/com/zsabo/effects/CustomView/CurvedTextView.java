package com.zsabo.effects.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class CurvedTextView extends View {
    private Path myArc;
    private Paint mPaintText;
    private String textContent;

    public CurvedTextView(Context context, String text) {
        super(context);
        textContent = text;
        myArc = new Path();
        RectF oval = new RectF(30, 30, 240, 240);
        myArc.addArc(oval, 220, 345);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(35f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawTextOnPath(textContent+textContent, myArc, 0, 0, mPaintText);
    }
}