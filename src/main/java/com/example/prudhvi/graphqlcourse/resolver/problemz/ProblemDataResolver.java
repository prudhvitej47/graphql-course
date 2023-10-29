package com.example.prudhvi.graphqlcourse.resolver.problemz;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.Problem;
import com.example.prudhvi.graphqlcourse.generated.types.ProblemCreateInput;
import com.example.prudhvi.graphqlcourse.generated.types.ProblemResponse;
import com.example.prudhvi.graphqlcourse.service.ProblemzService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ProblemDataResolver {

    private final ProblemzService problemzService;

    public ProblemDataResolver(ProblemzService problemzService) {
        this.problemzService = problemzService;
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemLatestList)
    public List<Problem> getLatestProblemList() {
        return problemzService.findAllLatestProblem();
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemById)
    public Problem getProblemById(@InputArgument("number") String problemId) {
        return problemzService.findByProblemId(UUID.fromString(problemId)).orElse(null);
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(
            @RequestHeader(value = "authToken") String authToken,
            @InputArgument("problem") ProblemCreateInput problemCreateInput) {
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> problemAdded() {
        return null;
    }
}
