package edu.hm.cs.fwp.cloud.tool.helm.core;

import edu.hm.cs.fwp.cloud.tools.helm.core.ExecutableRunner;
import edu.hm.cs.fwp.cloud.tools.helm.core.Slf4jLoggerAdapter;
import org.junit.Test;

import java.nio.file.Paths;

public class ExcecutableRunnerTest {

    @Test
    public void executingRunnerWithSystemOut() {
        ExecutableRunner underTest = new ExecutableRunner();
        underTest.run(Paths.get(".").toAbsolutePath().toFile(), System.out::println, "helm","version");
    }

    @Test
    public void executingRunnerWithSlf4jLogger() {
        ExecutableRunner underTest = new ExecutableRunner();
        underTest.run(Paths.get(".").toAbsolutePath().toFile(), new Slf4jLoggerAdapter(), "helm","version");
    }
}
