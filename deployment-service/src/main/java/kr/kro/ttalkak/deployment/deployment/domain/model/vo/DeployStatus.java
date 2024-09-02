package kr.kro.ttalkak.deployment.deployment.domain.vo;

import lombok.Getter;

@Getter
public enum DeployStatus {
    READY, ERROR, BUILDING, QUEUED, CANCELED
}
