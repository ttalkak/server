package kr.kro.ttalkak.deployment.deployment.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeploymentCreateCommand {

    private Long projectId;
}
