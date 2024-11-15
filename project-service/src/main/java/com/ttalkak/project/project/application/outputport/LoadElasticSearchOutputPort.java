package com.ttalkak.project.project.application.outputport;

import com.ttalkak.project.project.framework.web.request.SearchLogRequest;
import com.ttalkak.project.project.framework.web.response.LogHistogramResponse;
import com.ttalkak.project.project.framework.web.response.LogPageResponse;
import com.ttalkak.project.project.framework.web.response.MonitoringInfoResponse;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

import java.time.Instant;
import java.util.List;

public interface LoadElasticSearchOutputPort {

    /** 페이징 처리한 로그 조회 */
    LogPageResponse getLogsByPageable(SearchLogRequest searchLogRequest) throws Exception;

    /** 히스토그램 로그 조회 */
    List<LogHistogramResponse> getLogHistogram(Instant from, Instant to, Long deploymentId, DateHistogramInterval interval) throws Exception;

    /** AI 모니터링 정보 제공 */
    MonitoringInfoResponse getAIMonitoringInfo(String deploymentId) throws Exception;

}
