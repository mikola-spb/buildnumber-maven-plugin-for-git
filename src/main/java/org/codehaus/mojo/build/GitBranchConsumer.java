package org.codehaus.mojo.build;


import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.util.AbstractConsumer;

public class GitBranchConsumer extends AbstractConsumer {
    private static final String DETACHED = "DETACHED";
    private static final String CURRENT_BRANCH_PREFIX = "*";
    private static final String BRANCH_NAME_PATTERN = "[\\w/_-]+";
    private String branchName;

    public GitBranchConsumer(ScmLogger logger) {
        super(logger);
    }

    public void consumeLine(String line) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug("consume line " + line);
        }
        if (line.startsWith(CURRENT_BRANCH_PREFIX)) {
            String currentBranchName = line.substring(CURRENT_BRANCH_PREFIX.length()).trim();
            if (currentBranchName.matches(BRANCH_NAME_PATTERN)) {
                branchName = currentBranchName;
            } else {
                branchName = DETACHED;
            }
        }
    }

    public String getBranchName() {
        return branchName;
    }
}