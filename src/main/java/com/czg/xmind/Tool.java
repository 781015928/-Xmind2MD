package com.czg.xmind;

import org.apache.commons.cli.CommandLine;

public interface Tool {
    int run(CommandLine commandLine, Context context) throws Exception;
}
