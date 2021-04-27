package com.czg.xmind.impl;

import com.czg.xmind.Context;
import com.czg.xmind.config.XMind2MDConfig;

import java.io.File;

public class ContextImpl implements Context {
    private XMind2MDConfig config;
    private File assetDir;

    public ContextImpl() {
    }


    public void setConfig(XMind2MDConfig config) {
        this.config = config;
    }

    @Override
    public XMind2MDConfig getConfig() {
        return config;
    }

    @Override
    public File getAssetDir() {
        if (assetDir == null) {
            initAssetDir();
        }
        return assetDir;
    }

    private void initAssetDir() {
        String dir = config.getProperty(XMind2MDConfig.NODE_ASSET_DIR_KEY);
        assetDir = new File(dir);
    }

    @Override
    public String getAssetDirPath() {
        if (assetDir == null) {
            initAssetDir();
        }
        return assetDir.getPath();
    }
}
