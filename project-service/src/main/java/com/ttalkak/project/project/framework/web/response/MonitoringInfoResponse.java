package com.ttalkak.project.project.framework.web.response;

import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public class MonitoringInfoResponse {

    private final long totalErrors;
    private final double avgResponseTime;
    private final List<AccessIpInfo> accessIpInfos;
    private final List<UsedMethodInfo> usedMethodInfos;
    private final List<ErrorCategory> errorCategories;

    @Builder
    public static class AccessIpInfo {
        private final String ip;
        private final long count;
    }

    @Builder
    public static class UsedMethodInfo {
        private final String method;
        private final long count;
    }

    @Builder
    public static class ErrorCategory {
        private final String category;
        private final long count;
        private final List<ErrorPath> topPaths;

        List<ErrorPath> getTopPaths() {
            return Collections.unmodifiableList(topPaths);
        }
    }

    @Builder
    public static class ErrorPath {
        private final String path;
        private final long count;
    }

    List<AccessIpInfo> getAccessIpInfos() {
        return Collections.unmodifiableList(accessIpInfos);
    }

    List<UsedMethodInfo> getUsedMethodInfos() {
        return Collections.unmodifiableList(usedMethodInfos);
    }

    List<ErrorCategory> getErrorCategories() {
        return Collections.unmodifiableList(errorCategories);
    }



}
