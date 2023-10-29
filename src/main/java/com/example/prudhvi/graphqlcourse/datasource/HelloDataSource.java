package com.example.prudhvi.graphqlcourse.datasource;

import com.example.prudhvi.graphqlcourse.generated.types.Hello;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelloDataSource {

    private final Faker faker;
    public static final List<Hello> HELLO_LIST = new ArrayList<>();

    public HelloDataSource(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    private void initializeDataSource() {

        for (int i = 0; i < 20; i++) {
            var hello = Hello.newBuilder()
                    .randomNumber(faker.random().nextInt(1000))
                    .text(faker.company().name())
                    .build();

            HELLO_LIST.add(hello);
        }
    }
}
