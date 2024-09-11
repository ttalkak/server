package com.ttalkak.deployment.deployment.framework.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DatabaseCreateRequest {

    @NotNull(message = "데이터베이스 이름은 필수입니다.")
    private String databaseName;

    @NotNull(message = "데이터베이스 포트는 필수입니다.")
    private int databasePort;

    private String username;

    @NotNull(message = "비밀번호는 필수입니다.")
    private String password;

    @Builder
    private DatabaseCreateRequest(String databaseName, int databasePort, String username, String password) {
        this.databaseName = databaseName;
        this.databasePort = databasePort;
        this.username = username;
        this.password = password;
    }
}
