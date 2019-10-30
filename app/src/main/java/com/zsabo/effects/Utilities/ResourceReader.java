package com.zsabo.effects.Utilities;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;

import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ResourceReader {

    private static ResourceReader mInstance = null;

    private ResourceReader() {
    }

    public static ResourceReader getInstance() {
        if (mInstance == null) {
            mInstance = new ResourceReader();
        }
        return mInstance;
    }

    public ArrayList<AudioFile> getAudioFiles(Context context) {
        Field[] fields = R.raw.class.getFields();
        ArrayList<AudioFile> audioFiles = new ArrayList<>();

        for (Field field : fields) {
            int resourceID = getResourceId(context, field);
            String title = getSoundTitle(context, resourceID);

            if (title != null && !title.isEmpty()) {
                audioFiles.add(new AudioFile(title, MediaPlayer.create(context, resourceID),context));
            }
        }
        return audioFiles;
    }

    public ArrayList<String> getAudioFileNames(Context context) {
        Field[] fields = R.raw.class.getFields();
        ArrayList<String> audioFileNames = new ArrayList<>();

        for (Field field : fields) {
            audioFileNames.add(getSoundTitle(context, getResourceId(context, field)));
        }
        return audioFileNames;
    }

    private String getSoundTitle(Context context, int resourceID) {
        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(
                context,
                Uri.parse("android.resource://" + context.getPackageName() + "/" + resourceID));
        return metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
    }

    private int getResourceId(Context context, Field field) {
        return context.getResources().getIdentifier(
                field.getName(),
                "raw",
                context.getPackageName());
    }

}
