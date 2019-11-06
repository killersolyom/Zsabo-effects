package com.zsabo.effects.CustomView.AudioItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;

import java.math.BigDecimal;

public class AudioItemListenCounterView extends ConstraintLayout {

    private TextView counterTextView;

    public AudioItemListenCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_listen_counter_view, this, true);
        counterTextView = findViewById(R.id.item_listen_counter);
    }

    public void increaseCounter(AudioFile audioFile) {
        DataManager.getInstance().increaseListenCounter(audioFile.getTitle());
        displayCounter(audioFile);
    }

    public void displayCounter(AudioFile audioFile) {
        long number = DataManager.getInstance().getListenCounter(audioFile.getTitle());
        if (number > 0L) {
            String stringNumber = shortNumber(number);
            setTextSize(stringNumber);
            counterTextView.setText(stringNumber);
            counterTextView.setVisibility(VISIBLE);
        } else {
            counterTextView.setVisibility(GONE);
        }
    }

    private void setTextSize(String numberText) {
        switch (numberText.length()) {
            case 1:
            case 2:
                counterTextView.setTextSize(20);
                break;
            case 3:
                counterTextView.setTextSize(15);
                break;
            case 4:
                counterTextView.setTextSize(12);
                break;
            case 5:
                counterTextView.setTextSize(10);
                break;
            case 6:
                counterTextView.setTextSize(8);
                break;
        }
    }

    private String shortNumber(long number) {
        switch (String.valueOf(number).length()) {
            case 1:
            case 2:
            case 3:
                return String.valueOf(number);// number < 1000
            case 4:
            case 5:
            case 6:
                return formatNumber((float) number / 1000) + "K"; // number >= 1000
            case 7:
            case 8:
            case 9:
                return formatNumber((float) number / 1000000) + "M"; // number >= 1 000 000
            case 10:
            case 11:
            case 12:
                return formatNumber((float) number / 1000000000) + "G"; // number >= 1 000 000 000
            case 13:
            case 14:
            case 15:
                return "INF";
            default:
                return String.valueOf(0);
        }
    }

    private String formatNumber(float counter) {
        String number = String.valueOf(roundNumber(counter));
        if (!number.isEmpty()) {
            if (number.endsWith(".0")) {
                return number.substring(0, number.indexOf("."));
            }
        }
        return number;
    }

    private float roundNumber(float number) {
        try {
            BigDecimal bigdecimal = new BigDecimal(Float.toString(number));
            bigdecimal = bigdecimal.setScale(1, BigDecimal.ROUND_HALF_UP);
            return bigdecimal.floatValue();
        } catch (Exception e) {
            return 0;
        }
    }

}