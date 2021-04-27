package com.czg.xmind.bean;

import com.czg.xmind.bean.XMindNode;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MdDocument {


    private List<String> text = new ArrayList();
    private List<XMindNode> nodes = new ArrayList();


    public void addContent(XMindNode xMindNode) {
        text.add(xMindNode.getContentText());
        nodes.add(xMindNode);
    }


    public void println() {
        text.stream().forEach(System.out::println);
    }

    public void flush( File outPutFile) {
        if (outPutFile.exists()) {
            outPutFile.delete();
        }
        if (outPutFile.getParentFile() != null && outPutFile.getParentFile().isFile()) {
            outPutFile.getParentFile().delete();
        }
        if (outPutFile.getParentFile() != null && !outPutFile.getParentFile().exists()) {
            outPutFile.getParentFile().mkdirs();
        }
        nodes.forEach(it -> it.flush(outPutFile));
        PrintStream ps = null;
        try {
            ps = new PrintStream(outPutFile, "UTF-8");
            text.stream().forEach(ps::println);
            ps.flush();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }

}
