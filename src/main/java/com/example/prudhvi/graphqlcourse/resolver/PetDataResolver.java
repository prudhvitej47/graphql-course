package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.Cat;
import com.example.prudhvi.graphqlcourse.generated.types.Dog;
import com.example.prudhvi.graphqlcourse.generated.types.Pet;
import com.example.prudhvi.graphqlcourse.generated.types.PetFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.example.prudhvi.graphqlcourse.datasource.PetDataSource.PET_LIST;

@DgsComponent
public class PetDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Pets)
    public List<Pet> getPets(@InputArgument("petFilter") Optional<PetFilter> filter) {
        return filter.map(petFilter -> PET_LIST.stream()
                .filter(pet -> matchPetFilter(petFilter, pet))
                .toList()).orElse(PET_LIST);

    }

    private boolean matchPetFilter(PetFilter petFilter, Pet pet) {
        if (StringUtils.isBlank(petFilter.getPetType())) {
            return true;
        }

        var isPetTypeMatching = false;
        if (petFilter.getPetType().equalsIgnoreCase("Dog")) {
            isPetTypeMatching = pet instanceof Dog;
        } else if (petFilter.getPetType().equalsIgnoreCase("Cat")) {
            isPetTypeMatching = pet instanceof Cat;
        }

        return isPetTypeMatching &&
                (StringUtils.isBlank(pet.getName()) || StringUtils.containsIgnoreCase(pet.getName(), petFilter.getName()));
    }
}
