package com.czg.xmind.command;

import com.czg.xmind.Command;
import com.czg.xmind.Commander;
import com.czg.xmind.util.OptionsValue;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

@Commander(opt = "2md", argName = "转换成MD", desc = "将输入Xmind转换为MD文档", longOpt = "xmind2md")
public class XMind2MdCommand extends Command {


    @Override
    public String getToolClassPath() {
        return "com.czg.xmind.command.XMind2MD";
    }

    @Override
    protected void initArguments(Options options) {
        options.addOption(OptionsValue.INPUT_PATH);
    }


}
