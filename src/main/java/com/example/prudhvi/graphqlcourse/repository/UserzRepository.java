package com.example.prudhvi.graphqlcourse.repository;

import com.example.prudhvi.graphqlcourse.model.Userz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserzRepository extends CrudRepository<Userz, UUID> {

    Optional<Userz> findByUsernameIgnoreCase(String username);

    @Query(nativeQuery = true, value = """
        select u.*
            from userz u
            join userz_token ut on u.id = ut.user_id
        where ut.auth_token = ? and ut.expiry_at > current_timestamp
    """)
    Optional<Userz> findUserzByAuthToken(String authToken);
}
