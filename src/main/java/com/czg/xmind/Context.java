package com.czg.xmind;

import com.czg.xmind.config.XMind2MDConfig;

import java.io.File;

public interface Context {

    XMind2MDConfig getConfig();

    File getAssetDir();

    String getAssetDirPath();
}
