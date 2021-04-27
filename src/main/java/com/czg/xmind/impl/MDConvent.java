package com.czg.xmind.impl;

import com.czg.xmind.bean.MdDocument;
import com.czg.xmind.bean.XMindDocument;
import com.czg.xmind.bean.XMindNode;

import java.io.File;

public class MDConvent {

    public MdDocument write(XMindDocument document) {
        MdDocument mdDocument = new MdDocument();
        pushText(mdDocument, document);
        return mdDocument;
    }

    private void pushText(MdDocument mdDocument, XMindNode currentNode) {
        mdDocument.addContent(currentNode);
        for (XMindNode node : currentNode.getNodes()) {
            pushText(mdDocument, node);
        }
    }

}
