package com.czg.xmind.util;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xmind.core.IManifest;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;
import org.xmind.core.internal.Topic;
import org.xmind.core.internal.dom.ManifestImpl;
import org.xmind.core.internal.dom.TopicImpl;
import org.xmind.core.internal.dom.WorkbookImpl;
import org.xmind.core.io.ByteArrayStorage;
import org.xmind.core.io.IStorage;
import org.xmind.core.util.DOMUtils;
import org.xmind.core.util.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class DomUtil {

    public static InputStream getImage(ITopic topic) {
        String source = topic.getImage().getSource().replace("xap:", "");
        IWorkbook ownedWorkbook = topic.getOwnedWorkbook();
        IManifest manifest = ownedWorkbook.getManifest();
        return manifest.getFileEntry(source).getInputStream();
    }


    public static String getImageMediaType(ITopic topic) {
        String source = topic.getImage().getSource().replace("xap:", "");
        IWorkbook ownedWorkbook = topic.getOwnedWorkbook();
        IManifest manifest = ownedWorkbook.getManifest();
        return manifest.getFileEntry(source).getMediaType();
    }
}
