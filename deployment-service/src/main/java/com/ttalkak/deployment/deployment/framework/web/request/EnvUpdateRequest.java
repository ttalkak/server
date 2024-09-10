package com.ttalkak.deployment.deployment.framework.web.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnvUpdateRequest {

    private String key;

    private String value;
}
