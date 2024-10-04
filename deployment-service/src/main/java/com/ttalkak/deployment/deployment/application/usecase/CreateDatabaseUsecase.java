package com.ttalkak.deployment.deployment.application.usecase;

import com.ttalkak.deployment.common.UseCase;
import com.ttalkak.deployment.deployment.framework.web.request.DatabaseCreateRequest;
import com.ttalkak.deployment.deployment.framework.web.response.DatabaseResponse;


@UseCase
public interface CreateDatabaseUsecase {

    DatabaseResponse createDatabase(Long userId, DatabaseCreateRequest databaseCreateRequest);
}
