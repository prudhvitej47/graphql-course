package com.example.prudhvi.graphqlcourse.repository;

import com.example.prudhvi.graphqlcourse.model.UserzToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserzTokenRepository extends CrudRepository<UserzToken, UUID> {
}
