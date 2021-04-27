package com.czg.xmind.em;

import java.util.Arrays;
import java.util.Objects;

public enum ImageType {
    BASE64() {
        @Override
        public String getImageMd(String localPath) {
            return String.format("![%s](%s)", localPath, localPath);
        }
    }, LOCAL() {
        @Override
        public String getImageMd(String localPath) {
            return String.format("![%s](%s)", localPath, localPath);
        }
    };


    public static ImageType getType(String name) {
        if (name == null) return LOCAL;
        return Arrays.asList(ImageType.values())
                .stream()
                .filter(it -> Objects.equals(name.toUpperCase(), it.name().toUpperCase()))
                .findFirst()
                .orElse(LOCAL);
    }

    public abstract String getImageMd(String localPath);
}
