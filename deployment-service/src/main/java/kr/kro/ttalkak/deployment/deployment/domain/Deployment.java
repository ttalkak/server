package kr.kro.ttalkak.deployment.deployment.domain;

import lombok.Data;

@Data
public class Deployment {
    private Long id;
    private Long projectId;
    private boolean status;
    private String url;
}
