package com.example.prudhvi.graphqlcourse.resolver.problemz;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.Solution;
import com.example.prudhvi.graphqlcourse.generated.types.SolutionCreateInput;
import com.example.prudhvi.graphqlcourse.generated.types.SolutionResponse;
import com.example.prudhvi.graphqlcourse.generated.types.SolutionVoteInput;
import com.example.prudhvi.graphqlcourse.service.SolutionzService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
public class SolutionDataResolver {

    private final SolutionzService solutionzService;

    public SolutionDataResolver(SolutionzService solutionzService) {
        this.solutionzService = solutionzService;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionCreate)
    public SolutionResponse createSolution(
            @RequestHeader(value = "authToken") String authToken,
            @InputArgument("solution") SolutionCreateInput solutionCreateInput) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionVote)
    public SolutionResponse voteForSolution(
            @RequestHeader(value = "authToken") String authToken,
            @InputArgument("solution") SolutionVoteInput solutionVoteInput) {
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION.TYPE_NAME, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> solutionVoteChanged(@InputArgument("solutionId") String solutionId) {
        return null;
    }
}
