package com.czg.xmind.em;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum MediaType {

    JPG(".jpg", "IMAGE/JPG"),
    PNG(".png", "IMAGE/PNG"),
    GIF(".gif", "IMAGE/GIF"),
    BMP(".bmp", "IMAGE/BMP"),
    JPEG(".jpg", "IMAGE/JPEG"),
    ;
    /**
     * filePre
     */
    private String suffix;
    private String mediaType;

    MediaType(String suffix, String mediaType) {
        this.suffix = suffix;
        this.mediaType = mediaType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public static MediaType parseType(String mediaType) {
        if (mediaType == null) return null;
        return Arrays.asList(MediaType.values())
                .stream()
                .filter(it -> Objects.equals(mediaType.toUpperCase(), it.mediaType))
                .findFirst()
                .orElse(null);
    }

}
