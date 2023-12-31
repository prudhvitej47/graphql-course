package com.example.prudhvi.graphqlcourse.repository;

import com.example.prudhvi.graphqlcourse.model.Solutionz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolutionzRepository extends CrudRepository<Solutionz, UUID> {

    @Query(nativeQuery = true, value = """
        select *
            from solutionz s
        where upper(s.content) like upper(:keyword)
    """)
    List<Solutionz> findByKeyword(@Param("keyword") String keyword);
}
