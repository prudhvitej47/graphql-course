package com.example.prudhvi.graphqlcourse.service;

import com.example.prudhvi.graphqlcourse.generated.types.Solution;
import com.example.prudhvi.graphqlcourse.repository.SolutionzRepository;
import com.example.prudhvi.graphqlcourse.util.GraphqlUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionzService {

    private final SolutionzRepository solutionzRepository;

    public SolutionzService(SolutionzRepository solutionzRepository) {
        this.solutionzRepository = solutionzRepository;
    }

    public List<Solution> findAllSolutionByKeyword(String keyword) {
        return solutionzRepository.findByKeyword("%" + keyword + "%").stream()
                .map(GraphqlUtils::mapToGraphql)
                .toList();
    }
}
