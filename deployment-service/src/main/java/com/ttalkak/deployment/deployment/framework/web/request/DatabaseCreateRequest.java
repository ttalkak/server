package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DatabaseCreateRequest {

    private String databaseName;

    private int databasePort;

    private String username;

    private String password;
}
