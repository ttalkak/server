package com.ttalkak.deployment.deployment.domain.model.docker;

public class NpmStrategy implements PackageManagerStrategy {

    @Override
    public String copyDependencies() {
        return "COPY package*.json ./\n";
    }

    @Override
    public String installDependencies() {
        return "RUN npm ci\n";
    }

    @Override
    public String runBuild() {
        return "RUN npm run build\n";
    }
}
