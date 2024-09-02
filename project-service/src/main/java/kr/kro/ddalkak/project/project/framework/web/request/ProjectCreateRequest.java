package kr.kro.ddalkak.project.project.framework.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectCreateRequest {

    private Long userId;

    private String projectName;
}
