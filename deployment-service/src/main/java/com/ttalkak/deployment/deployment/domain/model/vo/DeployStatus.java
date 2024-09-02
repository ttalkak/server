package com.ttalkak.deployment.deployment.domain.model.vo;

import lombok.Getter;

@Getter
public enum DeployStatus {
    READY, ERROR, BUILDING, QUEUED, CANCELED
}
