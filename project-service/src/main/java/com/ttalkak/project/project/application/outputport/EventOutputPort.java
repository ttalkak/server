package com.ttalkak.project.project.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.project.project.domain.event.ProjectEvent;

public interface EventOutputPort {

    public void occurDeleteDeploymentInstance(ProjectEvent projectEvent) throws JsonProcessingException;

}
