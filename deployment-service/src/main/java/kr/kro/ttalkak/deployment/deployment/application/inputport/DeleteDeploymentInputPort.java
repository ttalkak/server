package kr.kro.ttalkak.deployment.deployment.application.inputport;

import kr.kro.ttalkak.deployment.deployment.application.outputport.DeploymentOutputPort;
import kr.kro.ttalkak.deployment.deployment.application.usecase.DeleteDeploymentUsecase;
import kr.kro.ttalkak.deployment.deployment.domain.DeploymentEntity;
import kr.kro.ttalkak.deployment.deployment.framework.web.dto.DeploymentDeleteInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDeploymentInputPort implements DeleteDeploymentUsecase {


    private final DeploymentOutputPort deploymentOutputPort;
    @Override
    public void deleteDeployment(DeploymentDeleteInputDTO deploymentDeleteInputDTO) {
        DeploymentEntity deploymentEntity = deploymentOutputPort.findDeployment(deploymentDeleteInputDTO.getDeploymentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 배포아이디는 존재하지 않습니다."));
        deploymentOutputPort.delete(deploymentEntity);
    }
}
