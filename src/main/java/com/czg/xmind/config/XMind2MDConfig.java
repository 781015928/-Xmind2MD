package com.czg.xmind.config;

import java.util.Properties;

public class XMind2MDConfig extends Properties {


    public static final String NODE_FACTORY_CLASS_KEY = "xmind.node-factory.class";

    private static final String NODE_FACTORY_CLASS_DEFAULT = "com.czg.xmind.impl.NodeFactoryImpl";

    public static final String NODE_IMAGE_MD_TYPE_KEY = "xmind.node-image-md-type";

    public static final String NODE_IMAGE_MD_TYPE_DEFAULT = "LOCAL";

    public static final String NODE_ASSET_DIR_KEY = "xmind.node-asset.dir";

    public static final String NODE_ASSET_DIR_KEY_DEFAULT = "asset";

    public XMind2MDConfig() {
        initConfig();
    }

    public XMind2MDConfig(Properties defaults) {
        super(defaults);
        initConfig();
    }

    private void initConfig() {
        if (getProperty(NODE_FACTORY_CLASS_KEY) == null) {
            setProperty(NODE_FACTORY_CLASS_KEY, NODE_FACTORY_CLASS_DEFAULT);
        }

        if (getProperty(NODE_IMAGE_MD_TYPE_KEY) == null) {
            setProperty(NODE_IMAGE_MD_TYPE_KEY, NODE_IMAGE_MD_TYPE_DEFAULT);
        }
        if (getProperty(NODE_ASSET_DIR_KEY) == null) {
            setProperty(NODE_ASSET_DIR_KEY, NODE_ASSET_DIR_KEY_DEFAULT);
        }
    }
}
