package kr.kro.ttalkak.deployment.deployment.framework.web;

import kr.kro.ttalkak.deployment.deployment.application.usecase.CreateDeploymentUsecase;
import kr.kro.ttalkak.deployment.deployment.application.usecase.DeleteDeploymentUsecase;
import kr.kro.ttalkak.deployment.deployment.application.usecase.InquiryUsecase;
import kr.kro.ttalkak.deployment.deployment.application.usecase.UpdateDeploymentUsecase;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentDeleteInputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentCreateInputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentOutputDTO;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentUpdateInputDTO;
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
    private final InquiryUsecase inquiryUsecase;

    // 배포 등록
    @PostMapping("/deployment")
    public ResponseEntity<DeploymentOutputDTO> createDeployment(DeploymentCreateInputDTO deploymentCreateInputDTO){
        DeploymentOutputDTO deployment = createDeploymentUsecase.createDeployment(deploymentCreateInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(deployment);
    }

    // 하나의 프로젝트에 포함되는 배포이력 전체조회
    @GetMapping("/deployment/project/{projectId}")
    public ResponseEntity<List<DeploymentOutputDTO>> getAllDeploymentByProjectId(@PathVariable("projectId") Long projectId){
        List<DeploymentOutputDTO> deployments = inquiryUsecase.getDeploymentsByProjectId(projectId);
        return ResponseEntity.ok(deployments);
    }

    // 배포 상세조회
    @GetMapping("/deployment/{deploymentId}")
    public ResponseEntity<DeploymentOutputDTO> getDeployment(@PathVariable("deploymentId") Long deploymentId){
        DeploymentOutputDTO deployment = inquiryUsecase.getDeployment(deploymentId);
        return ResponseEntity.ok(deployment);
    }

    // 배포 검색조회
    @GetMapping("/deployment")
    public ResponseEntity<List<DeploymentOutputDTO>> searchDeploymentByGithubRepositoryName(
            @RequestParam(value = "githubRepoName") String githubRepoName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        List<DeploymentOutputDTO> deployments = inquiryUsecase.searchDeploymentByGithubRepositoryName(githubRepoName, page, size);
        return ResponseEntity.ok(deployments);
    }

    // 배포 수정 - 배포생성을 완료한 이후에 다시 손봐야할듯.
    @PatchMapping("/deployment")
    public ResponseEntity<DeploymentOutputDTO> updateDeployment(DeploymentUpdateInputDTO deploymentUpdateInputDTO){
        DeploymentOutputDTO deployment = updateDeploymentUsecase.updateDeployment(deploymentUpdateInputDTO);
        return ResponseEntity.ok().body(deployment);
    }

    // 배포 삭제
    @DeleteMapping("/deployment")
    public ResponseEntity<Void> deleteDeployment(DeploymentDeleteInputDTO deploymentDeleteInputDTO){
        deleteDeploymentUsecase.deleteDeployment(deploymentDeleteInputDTO);
        return ResponseEntity.ok().build();
    }


}
