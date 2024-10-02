package com.ttalkak.deployment.deployment.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseEvent implements Serializable {

    private Long databaseId;

    private String name;

    private String databaseType;

    private String username;

    private String password;
}
