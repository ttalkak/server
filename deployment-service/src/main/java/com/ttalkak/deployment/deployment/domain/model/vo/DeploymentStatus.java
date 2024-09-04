package com.ttalkak.deployment.deployment.domain.model.vo;

import lombok.Getter;

@Getter
public enum DeploymentStatus {
    READY, RUNNING, ERROR, DELETED;


    public static boolean isAlive(DeploymentStatus deploymentStatus) {
        if(deploymentStatus == DELETED){
            return false;
        }
        return true;
    }
}
