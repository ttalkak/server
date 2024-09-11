package com.ttalkak.deployment.deployment.framework.web;

import com.ttalkak.deployment.common.ApiResponse;
import com.ttalkak.deployment.deployment.application.usecase.*;
import com.ttalkak.deployment.deployment.framework.web.request.*;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class DeploymentController {

    private final CreateDeploymentUsecase createDeploymentUsecase;

    private final UpdateDeploymentUsecase updateDeploymentUsecase;

    private final CommandDeploymentStatusUsecase commandDeploymentStatusUsecase;

    private final DeleteDeploymentUsecase deleteDeploymentUsecase;

    private final InquiryUsecase inquiryUsecase;

    // 배포 등록
    @PostMapping("/deployment")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DeploymentResponse> createDeployment(@RequestBody DeploymentCreateRequest deploymentCreateRequest){
        DeploymentResponse deployment = createDeploymentUsecase.createDeployment(deploymentCreateRequest);
        return new ApiResponse(true, null, 201, deployment);
    }

    // 배포 상태 변경
    @PostMapping("/deployment/status")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> updateDeploymentStatus(@RequestBody DeploymentCommandStatusRequest deploymentCommandStatusRequest){
        commandDeploymentStatusUsecase.commandDeploymentStatus(deploymentCommandStatusRequest);
        return ApiResponse.success();
    }

    // 배포 상세조회
    @GetMapping("/deployment/{deploymentId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<DeploymentResponse> getDeployment(@PathVariable("deploymentId") Long deploymentId){
        DeploymentResponse deployment = inquiryUsecase.getDeployment(deploymentId);
        return ApiResponse.success(deployment);
    }

    // 배포 검색조회
    @GetMapping("/deployment")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<DeploymentResponse>> searchDeploymentByGithubRepositoryName(
            @RequestParam(value = "githubRepoName") String githubRepoName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        List<DeploymentResponse> deployments = inquiryUsecase.searchDeploymentByGithubRepositoryName(githubRepoName, page, size);
        return ApiResponse.success(deployments);
    }

    // 배포 수정
    @PatchMapping("/deployment")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<DeploymentResponse> updateDeployment(@RequestHeader("X-USER-ID") Long userId, @RequestBody DeploymentUpdateRequest deploymentUpdateRequest){
        DeploymentResponse deployment = updateDeploymentUsecase.updateDeployment(userId, deploymentUpdateRequest);
        return ApiResponse.success(deployment);
    }

    // 배포 삭제
    @DeleteMapping("/deployment/{deploymentId}")
    public ApiResponse deleteDeployment(@RequestHeader("X-USER-ID") Long userId, @PathVariable("deploymentId") Long deploymentId){
        deleteDeploymentUsecase.deleteDeployment(userId, deploymentId);
        return ApiResponse.success(null);
    }


}