package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.prudhvi.graphqlcourse.datasource.MobileAppDataSource.MOBILE_APP_LIST;

@DgsComponent
public class MobileAppDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.MobileApps)
    public List<MobileApp> getMobileApps(@InputArgument(value = "mobileAppFilter") Optional<MobileAppFilter> filter) {
        return filter
                .map(f -> MOBILE_APP_LIST.stream()
                        .filter(app -> doesFilterMatch(f, app))
                        .toList()
                )
                .orElse(MOBILE_APP_LIST);
    }

    private boolean doesFilterMatch(MobileAppFilter appFilter, MobileApp app) {
        var isAppMatch = StringUtils.containsIgnoreCase(app.getName(), StringUtils.defaultIfBlank(appFilter.getName(), "")) &&
                StringUtils.containsIgnoreCase(app.getVersion(), StringUtils.defaultIfBlank(appFilter.getVersion(), "")) &&
                app.getReleasedDate().isAfter(Optional.ofNullable(appFilter.getReleasedAfter()).orElse(LocalDate.MIN)) &&
                app.getDownloaded() >= Optional.ofNullable(appFilter.getMinDownload()).orElse(0);

        var isAppAndPlatformMatch = isAppMatch && (StringUtils.isBlank(appFilter.getPlatform()) || app.getPlatform().contains(appFilter.getPlatform()));

        var isAppPlatformAndAuthorMatch = isAppAndPlatformMatch &&
                (appFilter.getAuthor() == null ||
                        StringUtils.equalsIgnoreCase(app.getAuthor().getName(), StringUtils.defaultIfBlank(appFilter.getAuthor().getName(), ""))
                );

        return isAppPlatformAndAuthorMatch && (appFilter.getCategory() == null || appFilter.getCategory() == app.getCategory());
    }
}
