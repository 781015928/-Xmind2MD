package com.czg.xmind.impl;

import com.czg.xmind.AbstractComponent;
import com.czg.xmind.Context;
import com.czg.xmind.NodeFactory;
import com.czg.xmind.bean.XMindDocument;
import com.czg.xmind.bean.XMindNode;
import com.czg.xmind.config.XMind2MDConfig;
import com.czg.xmind.util.ReflectUtil;
import org.xmind.core.*;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

import static com.czg.xmind.config.XMind2MDConfig.*;

public class XMindReaderImpl extends AbstractComponent {

    private NodeFactory nodeFactory;

    public XMindReaderImpl(Context context) {
        super(context);
        initReader();
    }

    private void initReader() {
        nodeFactory = ReflectUtil.getInstance(getContext().getConfig().getProperty(NODE_FACTORY_CLASS_KEY), getContext());
    }

    public XMindDocument load(String path) {

        IWorkbookBuilder builder = Core.getWorkbookBuilder();// 初始化builder
        IWorkbook workbook = null;
        try {
            workbook = builder.loadFromFile(new File(path));// 打开XMind文件
        } catch (Exception e) {
            throw new RuntimeException(String.format("文件路径不正确 path=%s", path));
        }

        ISheet defSheet = workbook.getPrimarySheet();// 获取主Sheet
        ITopic rootTopic = defSheet.getRootTopic(); // 获取根Topic
        String className = rootTopic.getTitleText();//节点TitleText

        XMindDocument xMindDocument = new XMindDocument();
        xMindDocument.setContent(className);
        xMindDocument.setLevel(1);
        parseInternal(rootTopic, xMindDocument, xMindDocument);
        return xMindDocument;
    }

    private void parseInternal(ITopic topic, XMindNode parentNode, XMindNode root) {

        int index = 1;
        for (ITopic topicChild : topic.getAllChildren()) {
            XMindNode node = createNode(topicChild);
            node.setParent(parentNode);
            node.setRoot(root);
            node.setLevel(parentNode.getLevel() + 1);
            if (parentNode.isChildNotHaveIndex()) {
                node.setSkipIndex(parentNode.isChildNotHaveIndex());
                node.setChildNotHaveIndex(parentNode.isChildNotHaveIndex());
            }
            if (node.isSkipIndex()) {
                node.setIndex(-1);
            } else {
                node.setIndex(index++);
            }
            parseInternal(topicChild, node, root);
            parentNode.addNode(node);
        }
    }

    private XMindNode createNode(ITopic topic) {
        XMindNode node = nodeFactory.createNode(topic);
        node.setContext(getContext());
        return node;
    }
}
