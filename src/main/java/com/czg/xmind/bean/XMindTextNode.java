package com.czg.xmind.bean;

import com.czg.xmind.util.StringUtil;

public class XMindTextNode extends XMindNode {
    private String text;

    @Override
    public String getContentText() {
        StringBuilder builder = new StringBuilder();

        textFormat(builder);

        putLevelIndex(builder);

        builder.append(text)
                .append("\n");

        return builder.toString();
    }

    private void textFormat(StringBuilder builder) {
        if (level > 5) return;
        if (getNodes().size() == 0) return;
        builder.append(StringUtil.getLevelTextFormat(level));
    }

    private void putLevelIndex(StringBuilder builder) {
        if (getNodes().size() == 0) return;
        builder.append(getLevelText());
        builder.append(' ');
    }


    @Override
    public void setContent(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
