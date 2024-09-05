package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DatabaseCreateRequest {

    private String databaseName;

    private int databasePort;

    private String username;

    private String password;

    @Builder
    private DatabaseCreateRequest(String databaseName, int databasePort, String username, String password) {
        this.databaseName = databaseName;
        this.databasePort = databasePort;
        this.username = username;
        this.password = password;
    }
}
