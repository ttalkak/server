package com.ttalkak.deployment.deployment.domain.model.docker;

public interface PackageManagerStrategy {
    String copyDependencies();
    String installDependencies();
    String runBuild();
}
