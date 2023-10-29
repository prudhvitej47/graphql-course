package com.example.prudhvi.graphqlcourse.service;

import com.example.prudhvi.graphqlcourse.generated.types.User;
import com.example.prudhvi.graphqlcourse.generated.types.UserAuthToken;
import com.example.prudhvi.graphqlcourse.model.Userz;
import com.example.prudhvi.graphqlcourse.model.UserzToken;
import com.example.prudhvi.graphqlcourse.repository.UserzRepository;
import com.example.prudhvi.graphqlcourse.repository.UserzTokenRepository;
import com.example.prudhvi.graphqlcourse.util.EncryptionUtils;
import com.example.prudhvi.graphqlcourse.util.GraphqlUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.example.prudhvi.graphqlcourse.util.GraphqlUtils.mapToGraphql;

@Service
public class UserzService {

    private final UserzRepository userzRepository;
    private final UserzTokenRepository userzTokenRepository;

    public UserzService(UserzRepository userzRepository, UserzTokenRepository userzTokenRepository) {
        this.userzRepository = userzRepository;
        this.userzTokenRepository = userzTokenRepository;
    }

    public UserAuthToken login(String username, String password) {
        var optionalUser = userzRepository.findByUsernameIgnoreCase(username);

        if (optionalUser.isEmpty() ||
                !EncryptionUtils.isBcryptMatch(password, optionalUser.get().getHashedPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var randomAuthToken = RandomStringUtils.randomAlphanumeric(40);
        return mapToGraphql(refreshToken(optionalUser.get().getId(), randomAuthToken));
    }

    public Optional<User> findUserByAuthToken(String authToken) {
        return userzRepository.findUserzByAuthToken(authToken).map(GraphqlUtils::mapToGraphql);
    }

    private UserzToken refreshToken(UUID userId, String authToken) {
        var now = LocalDateTime.now();
        var expiryDate = now.plusHours(2);

        var userzToken = new UserzToken();
        userzToken.setId(userId);
        userzToken.setAuthToken(authToken);
        userzToken.setCreatedAt(now);
        userzToken.setExpiryAt(expiryDate);

        return userzTokenRepository.save(userzToken);
    }
}
