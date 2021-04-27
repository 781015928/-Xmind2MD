package com.czg.xmind.bean;

import com.czg.xmind.util.StringUtil;

public class XMindDocument extends XMindNode {

    private String title;


    @Override
    public String getContentText() {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtil.getLevelTextFormat(level))
       .append(title)
                .append("\n");

        return builder.toString();
    }

    @Override
    public void setContent(String content) {
        this.title = content;
    }

    @Override
    public String getLevelText() {
        return "";
    }

    @Override
    public String toString() {
        return title;
    }
}
