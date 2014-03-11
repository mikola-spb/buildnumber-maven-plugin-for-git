package org.codehaus.mojo.build;


import org.apache.maven.scm.log.DefaultLog;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GitBranchConsumerTest {
    private GitBranchConsumer gitBranchConsumer = new GitBranchConsumer(new DefaultLog());

    @Test
    public void consumeLine_current() throws Exception {
        gitBranchConsumer.consumeLine("* master");
        gitBranchConsumer.consumeLine("  another");

        assertEquals("master", gitBranchConsumer.getBranchName());
    }

    @Test
    public void consumeLine_noBranch() throws Exception {
        gitBranchConsumer.consumeLine("* (no branch)");
        assertEquals(null, gitBranchConsumer.getBranchName());
    }

    @Test
    public void consumeLine_detached() throws Exception {
        gitBranchConsumer.consumeLine("* (detached from e240007)");
        assertEquals(null, gitBranchConsumer.getBranchName());
    }

    @Test
    public void consumeLine_detached_takeNextAvailable() throws Exception {
        gitBranchConsumer.consumeLine("* (no branch)");
        gitBranchConsumer.consumeLine("* (detached from e240007)");
        gitBranchConsumer.consumeLine("  my-master");
        gitBranchConsumer.consumeLine("  another");

        assertEquals("my-master", gitBranchConsumer.getBranchName());
    }
}