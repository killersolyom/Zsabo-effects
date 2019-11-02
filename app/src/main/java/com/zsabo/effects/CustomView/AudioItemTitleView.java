package com.zsabo.effects.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.zsabo.effects.R;

public class AudioItemTitleView extends ConstraintLayout {


    public AudioItemTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_title_view, this, true);

    }

    public void setTitle(String title) {
        this.addView(new CurvedTextView(getContext(), title));
    }

    private class CurvedTextView extends View {
        private Path myArc;
        private Paint mPaintText;
        private String textContent;


        public CurvedTextView(Context context, String text) {
            super(context);
            Log.d("3ss","" + getHeight() + " " + getWidth());
            textContent = text;
            myArc = new Path();
            RectF oval = new RectF(45, 45, 255, 255);
            myArc.addArc(oval, 220, 345);
            mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaintText.setColor(Color.WHITE);
            mPaintText.setTextSize(35f);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawTextOnPath(textContent + textContent, myArc, 0, 0, mPaintText);
        }
    }

}