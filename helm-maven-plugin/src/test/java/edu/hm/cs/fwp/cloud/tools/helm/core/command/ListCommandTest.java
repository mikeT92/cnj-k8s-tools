package edu.hm.cs.fwp.cloud.tools.helm.core.command;

import static org.junit.Assert.*;

import edu.hm.cs.fwp.cloud.tools.helm.core.model.Release;
import org.junit.Test;

public class ListCommandTest {

    @Test
    public void listCommandWorksOk() throws Exception {
        ListCommand underTest = new ListCommand();
        ListCommandResult result = underTest.call();
        assertNotNull("command must return non-null result", result);
        assertNotNull("command must return list of releases", result.getReleases());
        for (Release current : result.getReleases()) {
            assertTrue("command must return releases with non-empty name", current.getName() != null && !current.getName().isEmpty());
        }
    }
}
