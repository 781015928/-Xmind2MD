package com.czg.xmind;

import com.czg.xmind.command.XMind2MdCommand;
import com.czg.xmind.command.XMindPtintlnCommand;
import com.czg.xmind.config.XMind2MDConfig;
import com.czg.xmind.exception.HelpException;
import com.czg.xmind.impl.ContextImpl;
import com.czg.xmind.util.ReflectUtil;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main extends Command implements Tool {
    private static Map<String, Command> commands = new HashMap<>();


    public static void main(String[] args) {
        // help

        Main main = new Main();
        main.getOptions().addOption("h", "help", false, "usage help");
        main.loadCommand(XMind2MdCommand.class);
        main.loadCommand(XMindPtintlnCommand.class);
        ContextImpl context = new ContextImpl();
        context.setConfig(new XMind2MDConfig(new Properties()));
        main.doMain(args, context);
    }


    @Override
    public int run(CommandLine commandLine, Context context) throws Exception {
        Option[] options = commandLine.getOptions();
        for (int i = 0; i < options.length; i++) {
            Command command = commands.get(options[i].getOpt());
            if (command != null) {
                command.doMain(commandLine.getArgs(), context);
                return 0;
            }

        }
        if (options.length == 1) {
            if ("h".equals(options[0].getOpt())) {
                throw new HelpException();
            }
        }
        throw new RuntimeException(" not Command");
    }


    @Override
    public String getToolClassPath() {
        return getClass().getName();
    }


    private void loadCommand(String classPath) {
        try {
            Class<Command> clazz = (Class<Command>) Class.forName(classPath);
            loadCommand(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("命令找不到", e);
        }
    }

    private void loadCommand(Class<? extends Command> clazz) {
        Commander commander = clazz.getAnnotation(Commander.class);
        Command command = ReflectUtil.getInstance(clazz);
        commands.put(commander.opt(), command);
        getOptions().addOption(Option.builder(commander.opt()).argName(commander.argName()).required(false).hasArg(false).longOpt(commander.longOpt()).type(String.class).desc(commander.desc()).build());

    }

}
