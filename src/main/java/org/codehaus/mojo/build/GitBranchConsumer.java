package org.codehaus.mojo.build;


import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.util.AbstractConsumer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitBranchConsumer extends AbstractConsumer {
    private static final String BRANCH_NAME_PATTERN = "[*\\s]*([\\w-]+)";
    private String branchName;

    public GitBranchConsumer(ScmLogger logger) {
        super(logger);
    }

    public void consumeLine(String line) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug("consume line " + line);
        }
        Pattern branchNamePattern = Pattern.compile(BRANCH_NAME_PATTERN);
        Matcher branchNameMatcher = branchNamePattern.matcher(line);

        if (branchName == null && branchNameMatcher.matches()) {
            branchName = branchNameMatcher.group(1);
        }
    }

    public String getBranchName() {
        return branchName;
    }
}