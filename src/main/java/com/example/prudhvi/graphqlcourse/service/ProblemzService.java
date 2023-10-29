package com.example.prudhvi.graphqlcourse.service;

import com.example.prudhvi.graphqlcourse.generated.types.Problem;
import com.example.prudhvi.graphqlcourse.repository.ProblemzRepository;
import com.example.prudhvi.graphqlcourse.util.GraphqlUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemzService {

    private final ProblemzRepository problemzRepository;

    public ProblemzService(ProblemzRepository problemzRepository) {
        this.problemzRepository = problemzRepository;
    }

    public List<Problem> findAllLatestProblem() {
        return problemzRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(GraphqlUtils::mapToGraphql)
                .toList();
    }

    public Optional<Problem> findByProblemId(UUID problemzId) {
        var persistedProblemz = problemzRepository.findById(problemzId);
        return persistedProblemz.map(GraphqlUtils::mapToGraphql);

    }

    public List<Problem> findAllProblemByKeyword(String keyword) {
        return problemzRepository.findByKeyword("%" + keyword + "%").stream()
                .map(GraphqlUtils::mapToGraphql)
                .toList();
    }
}
