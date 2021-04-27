package com.czg.xmind.command;

import com.czg.xmind.Command;
import com.czg.xmind.Commander;
import com.czg.xmind.util.OptionsValue;
import org.apache.commons.cli.Options;

@Commander(opt = "p", argName = "打印到控制台", desc = "将输入Xmind打印到控制台", longOpt = "println")
public class XMindPtintlnCommand extends Command {


    @Override
    public String getToolClassPath() {
        return "com.czg.xmind.command.XMindPrintln";
    }

    @Override
    protected void initArguments(Options options) {
        options.addOption(OptionsValue.INPUT_PATH);
    }


}
