package com.ttalkak.deployment.deployment.framework.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DatabaseCreateRequest {

    @NotNull(message = "데이터베이스 이름을 필수입니다.")
    private String databaseName;


    @NotNull(message = "10000~20000의 포트넘버를 입력해주세요.")
    private int databasePort;

    @NotNull(message = "유저 이름은 필수입니다.")
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
