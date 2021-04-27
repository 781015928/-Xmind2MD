package com.czg.xmind.command;

import com.czg.xmind.Context;
import com.czg.xmind.bean.MdDocument;
import com.czg.xmind.Tool;
import com.czg.xmind.bean.XMindDocument;
import com.czg.xmind.config.XMind2MDConfig;
import com.czg.xmind.impl.MDConvent;
import com.czg.xmind.impl.XMindReaderImpl;
import org.apache.commons.cli.CommandLine;

import java.io.File;

public class XMindPrintln implements Tool {


    @Override
    public int run(CommandLine commandLine, Context context) {
        String inputPath = commandLine.getOptionValue("inputPath");
        XMindDocument document = new XMindReaderImpl(context).load(inputPath);
        MDConvent mdConvent = new MDConvent();
        MdDocument mdDocument = mdConvent.write(document);
        mdDocument.println();
        System.out.println();
        return 0;
    }
}