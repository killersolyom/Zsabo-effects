package com.zsabo.effects.CustomView.AudioItem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.R;

public class AudioItemTitleView extends ConstraintLayout {

    private String textContent;
    private final int maxLength = 36;
    private CurvedTextView curvedTextView;

    public AudioItemTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_title_view, this, true);

    }

    public void setTitle(String title) {
        curvedTextView = new CurvedTextView(getContext(), title);
        this.addView(curvedTextView);
    }

    public String getText() {
        return textContent;
    }

    public void removeTitle() {
        curvedTextView.postInvalidate();
    }

    private class CurvedTextView extends View {
        private Path myArc;
        private Paint mPaintText;

        public CurvedTextView(Context context, String text) {
            super(context);
            textContent = cropText(text);
        }

        private String cropText(String text) {
            if (text.length() >= maxLength) {
                return text.substring(0, maxLength) + "...";
            }
            return text;
        }

        private boolean setupSettings(int width, int height) {
            if (width == 0 || height == 0) {
                return false;
            }
            myArc = new Path();
            int widthOffset = (int) ((width / 100) * 14.5);
            int heightOffset = (int) ((height / 100) * 14.5);
            int newWidth = width - widthOffset;
            int newHeight = height - heightOffset;
            RectF oval = new RectF(widthOffset, heightOffset, newWidth, newHeight);
            myArc.addArc(oval, 220, 345);
            mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaintText.setColor(Color.WHITE);
            mPaintText.setTextSize(widthOffset + 5);
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (setupSettings(this.getWidth(), this.getHeight())) {
                canvas.drawTextOnPath(textContent, myArc, 0, 0, mPaintText);
            }
        }
    }

}