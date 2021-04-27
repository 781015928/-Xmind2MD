package com.czg.xmind;

import com.czg.xmind.config.XMind2MDConfig;

public abstract class AbstractComponent {


    protected final Context context;

    public AbstractComponent(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
