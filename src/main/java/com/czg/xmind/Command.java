package com.czg.xmind;

import com.czg.xmind.exception.HelpException;
import com.czg.xmind.util.ReflectUtil;
import org.apache.commons.cli.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Command {
    public static final String LINE = "----------------------------------------------------------------------";

    public abstract String getToolClassPath();


    public Command() {
        initArguments(options);
    }

    protected void initArguments(Options options) {
    }

    ;

    protected Options options = new Options();

    /**
     *
     */

    protected Options getOptions() {
        return options;
    }

    private String useAge;

    /**
     * get string of help usage
     *
     * @return help string
     */
    protected String getUsage() {
        if (useAge == null) {
            HelpFormatter helpFormatter = new HelpFormatter();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
            helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, getOptions().getOptions().stream().map(Option::getOpt).map(it -> "-" + it).collect(Collectors.joining(",", "[", "]")), LINE,
                    options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, LINE);

            printWriter.flush();
            useAge = new String(byteArrayOutputStream.toByteArray());
            printWriter.close();
        }
        return useAge;
    }


    /**
     * Parse and run the given command line. This may exit the JVM if
     * a nonzero exit code is returned from <code>run()</code>.
     */
    public void doMain(String args[], Context context) {
        try {
            /********
             */
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(getOptions(), args, true);
            Tool tool = ReflectUtil.getInstance(getToolClassPath());
            int ret = tool.run(commandLine, context);
            if (ret != 0) {
                System.exit(ret);
            }


        } catch (HelpException h) {
            System.out.println(getUsage());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.err.println(e.getMessage() + "\n" + getUsage());
            System.exit(-1);
        }
    }
}
