package com.ttalkak.deployment.deployment.application.usecase;


import com.ttalkak.deployment.common.UseCase;

@UseCase
public interface DeleteDatabaseUsecase {

    void deleteDatabase(Long userId, Long databaseId);
}
