package com.ttalkak.deployment.deployment.application.inputport;

import com.ttalkak.deployment.common.UseCase;
import com.ttalkak.deployment.deployment.application.usecase.CreateDockerfileUseCase;
import com.ttalkak.deployment.deployment.domain.model.docker.*;
import com.ttalkak.deployment.deployment.domain.model.vo.ServiceType;

@UseCase
public class CreateDockerfileInputPort implements CreateDockerfileUseCase {

    public String generateDockerfile(ServiceType serviceType, String buildTool, String packageManager, String languageVersion) {
        String dockerfileScript = "Dockerfile Not Exist";
        if (ServiceType.isBackendType(serviceType)) {
            DockerfileTemplate backendDockerfile = createBackendDockerfile(buildTool);
            dockerfileScript = backendDockerfile.generateDockerfileScript(
                    serviceType,
                    buildTool,
                    packageManager,
                    languageVersion
            );
        } else if (ServiceType.isFrontendType(serviceType)) {
            DockerfileTemplate frontendDockerfile = createFrontendDockerfile(buildTool);
            dockerfileScript = frontendDockerfile.generateDockerfileScript(
                    serviceType,
                    buildTool,
                    packageManager,
                    languageVersion
            );
        }
        return dockerfileScript;
    }

    private DockerfileTemplate createBackendDockerfile(String buildTool) {
        if (buildTool.equalsIgnoreCase("maven")) {
            return new BackendDockerfile(new MavenBuildToolStrategy());
        } else if (buildTool.equalsIgnoreCase("gradle")) {
            return new BackendDockerfile(new GradleBuildToolStrategy());
        }
        throw new IllegalArgumentException("Unsupported backend build tool: " + buildTool);
    }

    private DockerfileTemplate createFrontendDockerfile(String packageManager) {
        if (packageManager.equalsIgnoreCase("yarn")) {
            return new FrontendDockerfile(new YarnStrategy());
        } else if (packageManager.equalsIgnoreCase("npm")) {
            return new FrontendDockerfile(new NpmStrategy());
        }
        throw new IllegalArgumentException("Unsupported frontend package manager: " + packageManager);
    }
}
