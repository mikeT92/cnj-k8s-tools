package edu.hm.cs.fwp.cloud.tools.helm.core;

import java.util.Objects;

public final class KubectlBinaryLocator {

    private String location;

    public KubectlBinaryLocator(String location) {
        Objects.requireNonNull(location, "location must not be null or empty");
        this.location = location;
    }

    public String locate() {
        if (location == null) {
            location = resolveLocationFromEnvironment();
        }
        return location;
    }

    private String resolveLocationFromEnvironment() {
        String path = System.getenv("PATH");
        throw new UnsupportedOperationException();
    }
}
