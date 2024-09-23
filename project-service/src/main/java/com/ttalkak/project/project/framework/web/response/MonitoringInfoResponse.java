package com.ttalkak.project.project.framework.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

public class MonitoringInfoResponse {

    private final int totalErrors;
    private final List<ErrorCategory> errorCategories;

    @Builder
    public MonitoringInfoResponse(int totalErrors, List<ErrorCategory> errorCategories) {
        this.totalErrors = totalErrors;
        this.errorCategories = errorCategories;
    }

    public static class ErrorCategory {
        private final String category;
        private final int count;
        private final List<ErrorPath> topPaths;

        @Builder
        public ErrorCategory(String category, int count, List<ErrorPath> topPaths) {
            this.category = category;
            this.count = count;
            this.topPaths = topPaths;
        }

        List<ErrorPath> getTopPaths() {
            return Collections.unmodifiableList(topPaths);
        }
    }

    public static class ErrorPath {
        private final String path;
        private final int count;


        @Builder
        public ErrorPath(String path, int count) {
            this.path = path;
            this.count = count;
        }
    }

    List<ErrorCategory> getErrorCategories() {
        return Collections.unmodifiableList(errorCategories);
    }


}
