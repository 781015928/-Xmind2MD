package com.czg.xmind.bean;

import com.czg.xmind.config.XMind2MDConfig;
import com.czg.xmind.em.ImageType;
import com.czg.xmind.em.MediaType;
import com.czg.xmind.util.FileUtil;
import com.czg.xmind.util.StringUtil;
import org.xmind.core.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class XMindImageNode extends XMindNode {

    private String imageLocalPath;
    private static AtomicInteger imageCount = new AtomicInteger();
    private static String IMAGE_DEFAULT_NAME = "IMAGE_";
    private byte[] bytes;
    private String text;
    private MediaType mediaType;


    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    private static final String TEMPLATE = "\n%s\n<center>%s</center>\n";

    @Override
    public String getContentText() {
        if (imageLocalPath == null) {

            String fileName = (StringUtil.isNotEmpty(text) ? text + "_" : IMAGE_DEFAULT_NAME) + imageCount.incrementAndGet() + mediaType.getSuffix();


            String dirName = getContext().getAssetDirPath();

            imageLocalPath = dirName + File.separator + fileName;
        }
        String imageType = getContext().getConfig().getProperty(XMind2MDConfig.NODE_IMAGE_MD_TYPE_KEY);

        String imageMd = ImageType.getType(imageType).getImageMd(imageLocalPath);
        return String.format(TEMPLATE, imageMd, text);
    }


    @Override
    public void setContent(String text) {
        this.text = StringUtil.trim(text);
    }

    public void setImage(InputStream image) {
        try {
            int available = image.available();
            bytes = new byte[available];
            image.read(bytes);
        } catch (IOException e) {
            throw new RuntimeException("图片文件异常");
        } finally {
            try {
                image.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getLevelText() {
        return "";
    }

    @Override
    public void flush(File outPutPath) {
        File parentFile = outPutPath.getParentFile();
        File file = new File(parentFile, imageLocalPath);
        FileUtil.write(bytes, file);
    }

    @Override
    public String toString() {
        return imageLocalPath;
    }
}
