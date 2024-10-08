package com.ttalkak.deployment.deployment.domain.model.docker;

import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;

public class FrontendDockerfile extends DockerfileTemplate {

    private final PackageManagerStrategy packageManagerStrategy;

    public FrontendDockerfile(PackageManagerStrategy packageManagerStrategy) {
        this.packageManagerStrategy = packageManagerStrategy;
    }

    @Override
    protected String setupBaseImage(String buildTool, String languageVersion) {
        return "FROM node:" + languageVersion + " AS build\n";
    }

    @Override
    protected String runBuildCommand(String buildTool, String packageManager) {
        StringBuilder command = new StringBuilder();
        command.append(packageManagerStrategy.copyDependencies())
                .append(packageManagerStrategy.installDependencies())
                .append("COPY . .\n")
                .append(packageManagerStrategy.runBuild());
        return command.toString();
    }

    @Override
    protected String setupFinalStage(ServiceType serviceType, String buildTool, String languageVersion) {
        StringBuilder finalStage = new StringBuilder();
        finalStage.append("FROM nginx:stable-alpine\n");

        if ("cra".equalsIgnoreCase(buildTool)) {
            finalStage.append("COPY --from=build /app/build /usr/share/nginx/html\n");
        } else if ("vite".equalsIgnoreCase(buildTool)) {
            finalStage.append("COPY --from=build /app/dist /usr/share/nginx/html\n");
        }

        finalStage.append("CMD [\"nginx\", \"-g\", \"daemon off;\"]");
        return finalStage.toString();
    }
}
