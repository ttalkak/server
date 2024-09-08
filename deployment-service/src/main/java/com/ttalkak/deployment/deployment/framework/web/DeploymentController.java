package com.ttalkak.deployment.deployment.framework.web;

import com.ttalkak.deployment.deployment.application.usecase.*;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentDeleteRequest;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateStatusRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DeploymentResponse;
import com.ttalkak.deployment.deployment.framework.web.request.DeploymentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class DeploymentController {

    private final CreateDeploymentUsecase createDeploymentUsecase;
    private final UpdateDeploymentUsecase updateDeploymentUsecase;
    private final DeleteDeploymentUsecase deleteDeploymentUsecase;
    private final UpdateDeploymentStatusUsecase updateDeploymentStatusUsecase;
    private final InquiryUsecase inquiryUsecase;

    // 배포 등록
    @PostMapping("/deployment")
    @ResponseStatus(HttpStatus.CREATED)
    public DeploymentResponse createDeployment(@RequestBody DeploymentCreateRequest deploymentCreateRequest){
        DeploymentResponse deployment = createDeploymentUsecase.createDeployment(deploymentCreateRequest);
        return deployment;
    }

    // 하나의 프로젝트에 포함되는 배포이력 전체조회
    @GetMapping("/deployment/feign/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DeploymentResponse> getAllDeploymentByProjectId(@PathVariable("projectId") Long projectId){
        List<DeploymentResponse> deployments = inquiryUsecase.getDeploymentsByProjectId(projectId);
        return deployments;
    }

    // 배포 상세조회
    @GetMapping("/deployment/{deploymentId}")
    @ResponseStatus(HttpStatus.OK)
    public DeploymentResponse getDeployment(@PathVariable("deploymentId") Long deploymentId){
        DeploymentResponse deployment = inquiryUsecase.getDeployment(deploymentId);
        return deployment;
    }

    // 배포 검색조회
    @GetMapping("/deployment")
    @ResponseStatus(HttpStatus.OK)
    public List<DeploymentResponse> searchDeploymentByGithubRepositoryName(
            @RequestParam(value = "githubRepoName") String githubRepoName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        List<DeploymentResponse> deployments = inquiryUsecase.searchDeploymentByGithubRepositoryName(githubRepoName, page, size);
        return deployments;
    }

    // 배포 수정
    @PatchMapping("/deployment")
    @ResponseStatus(HttpStatus.OK)
    public DeploymentResponse updateDeployment(@RequestBody DeploymentUpdateRequest deploymentUpdateRequest){
        DeploymentResponse deployment = updateDeploymentUsecase.updateDeployment(deploymentUpdateRequest);
        return deployment;
    }

    // 배포 삭제
    @DeleteMapping("/deployment")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeployment(@RequestBody DeploymentDeleteRequest deploymentDeleteRequest){
        deleteDeploymentUsecase.deleteDeployment(deploymentDeleteRequest);
    }

    @PostMapping("/deployment/feign/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateDeploymentStatus(@RequestBody DeploymentUpdateStatusRequest deploymentUpdateStatusRequest){
        updateDeploymentStatusUsecase.updateDeploymentStatus(deploymentUpdateStatusRequest);
    }
}
