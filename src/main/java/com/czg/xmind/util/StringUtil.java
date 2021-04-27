package com.czg.xmind.util;

public class StringUtil {
    public static String getLevelTextFormat(int level) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < level; i++) {

            text.append('#');
        }

        text.append(' ');
        return text.toString();

    }

    public static boolean isNotEmpty(String text) {
        return text != null && !"".equals(text);
    }

    public static String trim(String text) {
        if (text == null) return "";
        return text.replace(" ", "");
    }

    public static boolean isNotEmpty(Object object) {
        return object != null && object.toString() != null && !"".equals(object.toString());
    }
}
