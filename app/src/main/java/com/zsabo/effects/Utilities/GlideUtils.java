package com.zsabo.effects.Utilities;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideUtils {

    private static GlideUtils mInstance = null;
    private Context context;

    private GlideUtils() {
    }

    public static GlideUtils getInstance() {
        if (mInstance == null) {
            mInstance = new GlideUtils();
        }
        return mInstance;
    }

    public void initialize(Context context) {
        this.context = context;
    }

    public void loadAudioImage(int id, ImageView imageView) {
        clearImage(imageView);
        Glide.with(context).load(id).override(250).fitCenter().into(imageView);
    }

    public void loadBackgroundImage(int id, ImageView imageView) {
        clearImage(imageView);
        Glide.with(context).load(id).centerCrop().into(imageView);
    }

    public void clearImage(ImageView imageView) {
        Glide.with(context).clear(imageView);
    }

    public void changeBackgroundWithFadeOut(int id, ImageView imageView) {
        Glide.with(context).load(id).centerCrop().transition(withCrossFade(500)).into(imageView);
    }
}
