package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.datasource.StockDataSource;
import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.Stock;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;

@DgsComponent
public class StockDataResolver {

    private final StockDataSource stockDataSource;

    public StockDataResolver(StockDataSource stockDataSource) {
        this.stockDataSource = stockDataSource;
    }

    // @DgsSubscription
    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.RandomStock)
    public Publisher<Stock> randomStock() {
        return Flux.interval(Duration.ofSeconds(3)).map(t -> stockDataSource.getRandomStock());
    }
}
