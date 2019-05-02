package edu.hm.cs.fwp.cloud.tools.helm.core.command;

import edu.hm.cs.fwp.cloud.tools.helm.core.ExecutableRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class InstallCommand implements Callable<InstallCommandResult> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String tillerNamespace;

    private File currentDirectory;

    public InstallCommand() {

    }

    public InstallCommand(Logger logger) {
        this.logger = logger;
    }

    private File getCurrentDirectory() {
        File result = null;
        if (this.currentDirectory != null) {
            result = this.currentDirectory;
        } else {
            try {
                result = new File(".").getCanonicalFile();
            } catch (IOException ex) {
                throw new UncheckedIOException("Failed to retrieve current directory!", ex);
            }
        }
        return result;
    }

    @Override
    public InstallCommandResult call() throws Exception {
        ExecutableRunner runner = new ExecutableRunner();
        Consumer<String> loggingConsumer = s -> this.logger.info(s);
        Consumer<String> consumer = loggingConsumer;
        runner.run(getCurrentDirectory(), consumer, "helm", "list");
        return new InstallCommandResult();
    }
}
