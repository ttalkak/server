package com.ttalkak.deployment.deployment.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttalkak.deployment.deployment.domain.event.CreateInstanceEvent;

public interface EventOutputPort {


    public void occurCreateInstance(CreateInstanceEvent createInstanceEvent) throws JsonProcessingException;
}