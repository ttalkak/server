package kr.kro.ttalkak.deployment.deployment.framework.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DatabaseCreateInputDTO {

    private String databaseName;

    private int databasePort;

    private String username;

    private String password;
}
