package edu.hm.cs.fwp.cloud.tools.helm.core.command;

import edu.hm.cs.fwp.cloud.tools.helm.core.ExecutableRunner;
import edu.hm.cs.fwp.cloud.tools.helm.core.model.Release;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ListCommand extends AbstractCommand<ListCommandResult> {

    private String tillerNamespace;

    public ListCommand() {
        super();
    }

    public ListCommand(Logger logger) {
        super(logger);
    }

    @Override
    protected ListCommandResult internalCall() throws Exception {
        ExecutableRunner runner = new ExecutableRunner();
        Consumer<String> loggingConsumer = s -> this.logger.info(s);
        ReleaseParser parsingConsumer = new ReleaseParser();
        Consumer<String> consumer = loggingConsumer.andThen(parsingConsumer);
        runner.run(getCurrentDirectory(), consumer, "helm", "list" );
        return new ListCommandResult(parsingConsumer.getReleases());
    }

    private static final class ReleaseParser implements Consumer<String> {

        private final List<Release> releases = new ArrayList<>();

        public List<Release> getReleases() {
            return releases;
        }

        @Override
        public void accept(String s) {
            if (s != null) {
                if (!s.startsWith("NAME")) {
                    Release release = parse(s);
                    if (release != null) {
                        releases.add(release);
                    }
                }
            }
        }

        private Release parse(String s) {
            Release result = null;
            int nextBlankPos = s.indexOf(" ");
            if (nextBlankPos > 0) {
                String releaseName = s.substring(0, nextBlankPos);
                result = new Release(releaseName);
            }
            return result;
        }
    }
}
