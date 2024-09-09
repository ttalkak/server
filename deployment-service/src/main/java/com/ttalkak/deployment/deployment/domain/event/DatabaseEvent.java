package com.ttalkak.deployment.deployment.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseEvent implements Serializable {
    private Long databaseId;

    private String databaseType;

    private String username;

    private String password;

    private int port;
}
