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
import java.util.Objects;


public class DomUtil {
    private static final String table = "org.xmind.ui.spreadsheet.column";

    public static InputStream getImage(ITopic topic) {
        String source = topic.getImage().getSource().replace("xap:", "");
        IWorkbook ownedWorkbook = topic.getOwnedWorkbook();
        IManifest manifest = ownedWorkbook.getManifest();
        return manifest.getFileEntry(source).getInputStream();
    }

    /**
     * 列竖向返回
     * 行横向返回
     *
     * @param topic
     * @return
     */
    public static boolean isTable(ITopic topic) {
        if (topic == null) return false;
        if (!(topic instanceof TopicImpl)) return false;
        if (((TopicImpl) topic).getImplementation() == null) return false;
        if (((TopicImpl) topic).getImplementation().getAttributes() == null) return false;
        if (((TopicImpl) topic).getImplementation().getAttributes().getNamedItem("structure-class") == null)
            return false;
        if (((TopicImpl) topic).getImplementation().getAttributes().getNamedItem("structure-class").getNodeValue() == null)
            return false;
        return ((TopicImpl) topic).getImplementation().getAttributes().getNamedItem("structure-class").getNodeValue().startsWith("org.xmind.ui.spreadsheet");
    }

    public static String getImageMediaType(ITopic topic) {
        String source = topic.getImage().getSource().replace("xap:", "");
        IWorkbook ownedWorkbook = topic.getOwnedWorkbook();
        IManifest manifest = ownedWorkbook.getManifest();
        return manifest.getFileEntry(source).getMediaType();
    }
}
