package com.zsabo.effects.Communication;

import com.zsabo.effects.CustomView.AudioItem.AudioItemView;

public interface AudioStreamInterface {

    void register(AudioItemView audioItemView);

    void unRegister(AudioItemView audioItem);

}
