package kr.kro.ttalkak.deployment.deployment.adapter.in.web;

import kr.kro.ttalkak.deployment.deployment.adapter.in.web.request.DeploymentCreateRequest;
import kr.kro.ttalkak.deployment.deployment.adapter.in.web.response.ApiResponse;
import kr.kro.ttalkak.deployment.deployment.application.port.in.DeploymentCreateCommand;
import kr.kro.ttalkak.deployment.deployment.application.port.in.DeploymentCreateUseCase;
import kr.kro.ttalkak.deployment.deployment.application.port.in.DeploymentDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/deployment")
@RequiredArgsConstructor
public class DeploymentController {

    private final DeploymentCreateUseCase deploymentCreateUseCase;
    private final DeploymentDeleteUseCase deploymentDeleteUseCase;

    @PostMapping("/create")
    public ApiResponse<Void> create(@RequestBody DeploymentCreateRequest request){

        // 서비스단의 dto를 생성한다.
        DeploymentCreateCommand command = new DeploymentCreateCommand(
                request.getProjectId()
        );

        // createService의 추상화인 reateUseCase에 의존한다.
        deploymentCreateUseCase.create(command);

        return ApiResponse.success();
    }




}
