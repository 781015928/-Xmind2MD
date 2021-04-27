package com.czg.xmind.bean;

import java.util.Arrays;
import java.util.List;

public class XMindCodeNode extends XMindNode {

    private String code;

    private static final List<String> codeTypes = Arrays.asList("bash", "java", "scala", "xml", "code", "css", "javascript", "c", "c++", "dart", "docker", "git", "groovy",
            "json", "go", "php", "ruby", "python", "kotlin", "sql", "yaml");


    @Override
    public String getContentText() {
        return code;
    }

    @Override
    public void setContent(String text) {
        code = formatCode(text);
    }

    @Override
    public String getLevelText() {
        return "";
    }

    @Override
    public String toString() {
        return code;
    }

    public String formatCode(String text) {
        if (text.startsWith("code:")) {
            text = text.replace("code:", "");
        }
        for (String codeType : codeTypes) {
            if (text.startsWith(codeType)) {
                text = text.replace(codeType, "");
                return "\n" + "```" + codeType + text + "\n```" + "\n";
            }

        }

        return "\n```\n" + text + "\n```\n";
    }
}
