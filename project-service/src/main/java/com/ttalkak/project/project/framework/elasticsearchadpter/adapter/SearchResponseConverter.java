package com.ttalkak.project.project.framework.elasticsearchadpter.adapter;

import com.ttalkak.project.project.framework.web.response.MonitoringInfoResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilters;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.util.ArrayList;
import java.util.List;

public class SearchResponseConverter {

    public static MonitoringInfoResponse toTopErrorSummaryResponse (SearchResponse searchResponse) {

        ParsedFilters errorTypeAgg = searchResponse.getAggregations().get("error_types");
        int totalErrors = 0;

        List<MonitoringInfoResponse.ErrorCategory> errorCategories = new ArrayList<>();

        for( Filters.Bucket bucket : errorTypeAgg.getBuckets()) {
            String category = bucket.getKeyAsString();
            long bucketDocCount = bucket.getDocCount();
            totalErrors += bucketDocCount;

            List<MonitoringInfoResponse.ErrorPath> errorPaths = new ArrayList<>();

            ParsedTerms topPathsAgg = bucket.getAggregations().get("top_paths");
            for(Terms.Bucket pathBucket : topPathsAgg.getBuckets()) {

                MonitoringInfoResponse.ErrorPath errorPath = MonitoringInfoResponse.ErrorPath.builder()
                        .path(pathBucket.getKeyAsString())
                        .count((int) pathBucket.getDocCount())
                        .build();

                errorPaths.add(errorPath);
            }

            MonitoringInfoResponse.ErrorCategory errorCategory = MonitoringInfoResponse.ErrorCategory.builder()
                    .category(category)
                    .count((int) bucketDocCount)
                    .topPaths(errorPaths)
                    .build();

            errorCategories.add(errorCategory);
        }

        MonitoringInfoResponse result = MonitoringInfoResponse.builder()
                .errorCategories(errorCategories)
                .totalErrors(totalErrors)
                .build();

        return result;
    }
}
