package com.czg.xmind.bean;

import com.czg.xmind.Context;
import com.czg.xmind.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class XMindNode {
    protected int level;
    private int index;
    private XMindNode parent;
    private XMindNode root;
    private Context context;
    private boolean skipIndex = false;

    private boolean childNotHaveIndex = false;

    public void setChildNotHaveIndex(boolean childNotHaveIndex) {
        this.childNotHaveIndex = childNotHaveIndex;
    }

    public boolean isChildNotHaveIndex() {
        return this.childNotHaveIndex;
    }

    public void setSkipIndex(boolean skipIndex) {
        this.skipIndex = skipIndex;
    }

    public boolean isSkipIndex() {
        return skipIndex;
    }


    public XMindNode getRoot() {
        return root;
    }


    public void setRoot(XMindNode root) {
        this.root = root;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public XMindNode getParent() {
        return parent;
    }

    public void setParent(XMindNode parent) {
        this.parent = parent;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    protected List<XMindNode> nodes = new ArrayList<>();

    public List<XMindNode> getNodes() {
        return nodes;
    }

    public void addNode(XMindNode node) {
        if (childNotHaveIndex) {
            node.setSkipIndex(true);
            node.setChildNotHaveIndex(childNotHaveIndex);
        }
        this.nodes.add(node);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract String getContentText();

    public abstract void setContent(String text);

    public String getLevelText() {
        if (skipIndex) return "";

        if (parent != null && StringUtil.isNotEmpty(parent.getLevelText())) {
            return parent.getLevelText() + "." + index;
        } else {
            return String.valueOf(index);
        }
    }

    public void flush(File outPutPath) {


    }

    @Override
    public abstract String toString();
}
