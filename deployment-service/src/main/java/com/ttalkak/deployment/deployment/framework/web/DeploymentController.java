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
    public ResponseEntity<DeploymentResponse> createDeployment(@RequestBody DeploymentCreateRequest deploymentCreateRequest){
        DeploymentResponse deployment = createDeploymentUsecase.createDeployment(deploymentCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(deployment);
    }

    // 하나의 프로젝트에 포함되는 배포이력 전체조회
    @GetMapping("/deployment/project/{projectId}")
    public ResponseEntity<List<DeploymentResponse>> getAllDeploymentByProjectId(@PathVariable("projectId") Long projectId){
        List<DeploymentResponse> deployments = inquiryUsecase.getDeploymentsByProjectId(projectId);
        return ResponseEntity.ok(deployments);
    }

    // 배포 상세조회
    @GetMapping("/deployment/{deploymentId}")
    public ResponseEntity<DeploymentResponse> getDeployment(@PathVariable("deploymentId") Long deploymentId){
        DeploymentResponse deployment = inquiryUsecase.getDeployment(deploymentId);
        return ResponseEntity.ok(deployment);
    }

    // 배포 검색조회
    @GetMapping("/deployment")
    public ResponseEntity<List<DeploymentResponse>> searchDeploymentByGithubRepositoryName(
            @RequestParam(value = "githubRepoName") String githubRepoName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        List<DeploymentResponse> deployments = inquiryUsecase.searchDeploymentByGithubRepositoryName(githubRepoName, page, size);
        return ResponseEntity.ok(deployments);
    }

    // 배포 수정 - 배포생성을 완료한 이후에 다시 손봐야할듯.
    @PatchMapping("/deployment")
    public ResponseEntity<DeploymentResponse> updateDeployment(DeploymentUpdateRequest deploymentUpdateRequest){
        DeploymentResponse deployment = updateDeploymentUsecase.updateDeployment(deploymentUpdateRequest);
        return ResponseEntity.ok().body(deployment);
    }

    // 배포 삭제
    @DeleteMapping("/deployment")
    public ResponseEntity<Void> deleteDeployment(DeploymentDeleteRequest deploymentDeleteRequest){
        deleteDeploymentUsecase.deleteDeployment(deploymentDeleteRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deployment/status")
    public ResponseEntity<Void> updateDeploymentStatus(DeploymentUpdateStatusRequest deploymentUpdateStatusRequest){
        updateDeploymentStatusUsecase.updateDeploymentStatus(deploymentUpdateStatusRequest);
        return ResponseEntity.ok().build();
    }
}
