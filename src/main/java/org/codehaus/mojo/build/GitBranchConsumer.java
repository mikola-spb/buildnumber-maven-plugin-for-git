package org.codehaus.mojo.build;


import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.util.AbstractConsumer;

public class GitBranchConsumer extends AbstractConsumer {
    public static final String CURRENT_BRANCH_PREFIX = "* ";
    private String branchName;

    public GitBranchConsumer(ScmLogger logger) {
        super(logger);
    }

    public void consumeLine(String line) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug("consume line " + line);
        }

        if (line.startsWith(CURRENT_BRANCH_PREFIX)) {
            handleCurrentBranchLine(line);
        }
    }

    public String getBranchName() {
        return branchName;
    }

    private void handleCurrentBranchLine(String line) {
        branchName = line.substring(CURRENT_BRANCH_PREFIX.length());
    }
}