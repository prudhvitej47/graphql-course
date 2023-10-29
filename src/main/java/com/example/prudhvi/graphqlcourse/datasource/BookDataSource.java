package com.example.prudhvi.graphqlcourse.datasource;

import com.example.prudhvi.graphqlcourse.generated.types.*;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class BookDataSource {

    private final Faker faker;
    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public BookDataSource(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    private void initializeBookDataSource() {
        for (int i = 0; i < 20; i++) {
            var addresses = new ArrayList<Address>();
            var author = Author.newBuilder()
                    .name(faker.book().author())
                    .originCountry(faker.country().name())
                    .addresses(addresses)
                    .build();
            var released = ReleaseHistory.newBuilder()
                    .year(faker.number().numberBetween(2010, 2023))
                    .printedEdition(faker.bool().bool())
                    .releasedCountry(faker.country().name())
                    .build();
            var book = Book.newBuilder()
                    .title(faker.book().title())
                    .publisher(faker.book().publisher())
                    .author(author)
                    .released(released)
                    .build();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++) {
                var address = Address.newBuilder()
                        .street(faker.address().streetAddress())
                        .city(faker.address().cityName())
                        .zipCode(faker.address().zipCode())
                        .country(faker.address().country())
                        .build();
                addresses.add(address);
            }

            BOOK_LIST.add(book);
        }
    }
}
