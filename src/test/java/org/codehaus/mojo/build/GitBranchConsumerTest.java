package org.codehaus.mojo.build;


import org.apache.maven.scm.log.DefaultLog;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GitBranchConsumerTest {
    private GitBranchConsumer gitBranchConsumer = new GitBranchConsumer(new DefaultLog());

    @Test
    public void consumeLine_current_validBranch() throws Exception {
        gitBranchConsumer.consumeLine("* master");
        gitBranchConsumer.consumeLine("  another");
        assertEquals("master", gitBranchConsumer.getBranchName());
    }

    @Test
    public void consumeLine_current_noBranch() throws Exception {
        gitBranchConsumer.consumeLine("* (no branch)");
        gitBranchConsumer.consumeLine("  another");
        assertEquals("DETACHED", gitBranchConsumer.getBranchName());
    }

    @Test
    public void consumeLine_current_detached() throws Exception {
        gitBranchConsumer.consumeLine("* (detached from e240007)");
        gitBranchConsumer.consumeLine("  another");
        assertEquals("DETACHED", gitBranchConsumer.getBranchName());
    }

    @Test
    public void consumeLine_onlyCurrentDetached() throws Exception {
        gitBranchConsumer.consumeLine("* (detached from e240007)");
        assertEquals("DETACHED", gitBranchConsumer.getBranchName());
    }

    @Test
    public void noLineConsumed() throws Exception {
        assertEquals(null, gitBranchConsumer.getBranchName());
    }
}