package com.czg.xmind.command;

import com.czg.xmind.Context;
import com.czg.xmind.bean.MdDocument;
import com.czg.xmind.Tool;
import com.czg.xmind.bean.XMindDocument;
import com.czg.xmind.config.XMind2MDConfig;
import com.czg.xmind.impl.MDConvent;
import com.czg.xmind.impl.XMindReaderImpl;
import com.czg.xmind.util.FileUtil;
import com.czg.xmind.util.StringUtil;
import org.apache.commons.cli.CommandLine;
import org.xmind.core.util.FileUtils;

import java.io.File;

public class XMind2MD implements Tool {


    @Override
    public int run(CommandLine commandLine, Context context) throws Exception {
        String inputPath = commandLine.getOptionValue("inputPath");
        File inputFile = new File(inputPath);
        String outputPath = commandLine.getOptionValue("outputPath");
        File outputFile = outputPath != null ? new File(outputPath) : null;
        if (!StringUtil.isNotEmpty(outputPath)) {
            String name = inputFile.getName().substring(0, inputFile.getName().indexOf('.'));
            outputPath = inputPath.replaceAll(".xmind", ".md");
            outputFile = new File(outputPath);
            String assetPath = name;
            FileUtil.clearDir(new File(outputFile.getParentFile(), assetPath));
            context.getConfig().setProperty(XMind2MDConfig.NODE_ASSET_DIR_KEY, assetPath);
        }

        XMindDocument document = new XMindReaderImpl(context).load(inputPath);
        MDConvent mdConvent = new MDConvent();
        MdDocument mdDocument = mdConvent.write(document);
        mdDocument.flush(outputFile);
        System.out.println(outputPath + "，生成成功");
        return 0;
    }
}