package com.example.prudhvi.graphqlcourse.datasource;

import com.example.prudhvi.graphqlcourse.generated.types.Cat;
import com.example.prudhvi.graphqlcourse.generated.types.Dog;
import com.example.prudhvi.graphqlcourse.generated.types.Pet;
import com.example.prudhvi.graphqlcourse.generated.types.PetFoodType;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetDataSource {

    private final Faker faker;
    public static final List<Pet> PET_LIST = new ArrayList<>();

    public PetDataSource(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    private void initializePetDataSource() {
        for (int i = 0; i < 20; i++) {
            Pet pet = switch (i % 2) {
                case 0:
                    yield Dog.newBuilder()
                            .name(faker.dog().name())
                            .food(PetFoodType.OMNIVORE)
                            .breed(faker.dog().breed())
                            .size(faker.dog().size())
                            .coatLength(faker.dog().size())
                            .build();
                default:
                    yield Cat.newBuilder()
                            .name(faker.cat().name())
                            .food(PetFoodType.CARNIVORE)
                            .breed(faker.cat().breed())
                            .registry(faker.cat().registry())
                            .build();
            };

            PET_LIST.add(pet);
        }
    }

}
