package com.example.prudhvi.graphqlcourse.datasource;

import com.example.prudhvi.graphqlcourse.generated.types.*;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MobileAppDataSource {

    private final Faker faker;
    public static final List<MobileApp> MOBILE_APP_LIST = new ArrayList<>();

    public MobileAppDataSource(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    private void initializeMobileAppDataSource() throws MalformedURLException {
        for (int i = 0; i < 20; i++) {
            var addresses = new ArrayList<Address>();
            var author = Author.newBuilder()
                    .name(faker.book().author())
                    .originCountry(faker.country().name())
                    .addresses(addresses)
                    .build();
            var mobileApp = MobileApp.newBuilder()
                    .name(faker.app().name())
                    .author(author)
                    .version(faker.app().version())
                    .platform(randomMobileAppPlatform())
                    .appId(UUID.randomUUID().toString())
                    .releasedDate(LocalDate.now().minusDays(faker.random().nextInt(365)))
                    .downloaded(faker.number().numberBetween(1, 1_500_000))
                    .homepage(new URL("https://" + faker.internet().url()))
                    .category(MobileAppCategory.values()[faker.random().nextInt(MobileAppCategory.values().length)])
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

            MOBILE_APP_LIST.add(mobileApp);
        }
    }

    private List<String> randomMobileAppPlatform() {
        return switch (ThreadLocalRandom.current().nextInt(10) % 3) {
            case 0 -> List.of("android");
            case 1 -> List.of("ios");
            default -> List.of("ios", "android");
        };
    }
}
