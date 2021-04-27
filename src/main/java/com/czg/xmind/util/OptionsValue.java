package com.czg.xmind.util;

import org.apache.commons.cli.Option;

public class OptionsValue {

    public static final Option INPUT_PATH = Option.builder("i")
            .argName("输入路径")
            .required(true)
            .hasArg(true)
            .longOpt("inputPath")
            .type(String.class)
            .desc("ximd本地文件输入路径")
            .build();


}
