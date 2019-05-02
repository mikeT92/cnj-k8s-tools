package edu.hm.cs.fwp.cloud.tools.helm.core.command;

import edu.hm.cs.fwp.cloud.tools.helm.core.model.Release;

import java.util.ArrayList;
import java.util.List;

public class ListCommandResult {

    private List<Release> releases = new ArrayList<>();

    public ListCommandResult(List<Release> releases) {
        this.releases.addAll(releases);
    }

    public List<Release> getReleases() {
        return releases;
    }
}
