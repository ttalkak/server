package com.ttalkak.deployment.deployment.framework.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DatabaseUpdateRequest {

    private Long id;

    private String databaseType;

    private String username;

    private String password;

    private int port;
}
