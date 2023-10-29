package com.example.prudhvi.graphqlcourse.datasource;

import com.example.prudhvi.graphqlcourse.generated.types.Stock;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class StockDataSource {

    private final Faker faker;

    public StockDataSource(Faker faker) {
        this.faker = faker;
    }

    public Stock getRandomStock() {
        return Stock.newBuilder()
                .symbol(faker.stock().nyseSymbol())
                .price(faker.random().nextInt(100, 1000))
                .lastTradeTime(OffsetDateTime.now())
                .build();
    }
}
