package com.ttalkak.deployment.deployment.domain.model.docker;

public interface BuildToolStrategy {
    String setupBaseImage(String languageVersion);
    String runBuildCommand();
    String copyArtifact();
}

