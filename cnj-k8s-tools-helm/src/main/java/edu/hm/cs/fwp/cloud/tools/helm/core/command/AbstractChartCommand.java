package edu.hm.cs.fwp.cloud.tools.helm.core.command;

import org.slf4j.Logger;

import java.io.File;

/**
 * Abstract base class of all {@code Helm} commands that require a {@code Chart} directory.
 * @param <V>
 */
public abstract class AbstractChartCommand<V> extends AbstractCommand<V> {

    private File chartDirectory;

    public AbstractChartCommand() {
        super();
    }

    public AbstractChartCommand(Logger logger) {
        super(logger);
    }

    public void setChartDirectory(File chartDirectory) {
        this.chartDirectory = chartDirectory;
    }

    public File getChartDirectory() {
        return chartDirectory;
    }
}
