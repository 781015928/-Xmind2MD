package com.czg.xmind.impl;

import com.czg.xmind.AbstractComponent;
import com.czg.xmind.Context;
import com.czg.xmind.NodeFactory;
import com.czg.xmind.bean.XMindCodeNode;
import com.czg.xmind.bean.XMindImageNode;
import com.czg.xmind.bean.XMindNode;
import com.czg.xmind.bean.XMindTextNode;
import com.czg.xmind.config.XMind2MDConfig;
import com.czg.xmind.em.MediaType;
import com.czg.xmind.util.DomUtil;
import org.xmind.core.ITopic;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class NodeFactoryImpl extends AbstractComponent implements NodeFactory {


    private NodeFactoryImpl(Context context) {
        super(context);
    }

    public XMindNode createNode(ITopic topic) {
        String titleText = topic.getTitleText();
        XMindNode node;
        if (isCode(titleText)) {
            node = new XMindCodeNode();
            node.setContent(titleText);
        } else if (isImage(topic)) {
            node = parseImageNode(topic);
        } else {
            node = new XMindTextNode();
            if (titleText != null && titleText.startsWith("@")) {
                titleText = titleText.replace("@", "");
                node.setChildNotHaveIndex(true);
            }
            if (titleText != null && titleText.startsWith("-")) {
                titleText = titleText.replace("-", "");
                node.setSkipIndex(true);
            }
            node.setContent(titleText);


        }
        return node;
    }

    private XMindNode parseImageNode(ITopic topic) {
        XMindImageNode node = new XMindImageNode();
        node.setContent(topic.getTitleText());
        InputStream inputStream = DomUtil.getImage(topic);
        node.setImage(inputStream);
        String mediaType = DomUtil.getImageMediaType(topic);
        MediaType type = MediaType.parseType(mediaType);
        node.setMediaType(type);
        return node;
    }

    private boolean isCode(String text) {
        return text.startsWith("code:");
    }

    private boolean isImage(ITopic iTopic) {
        return iTopic.getImage().getSource() != null;
    }

}
