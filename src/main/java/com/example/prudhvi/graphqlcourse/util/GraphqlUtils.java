package com.example.prudhvi.graphqlcourse.util;

import com.example.prudhvi.graphqlcourse.generated.types.*;
import com.example.prudhvi.graphqlcourse.model.Problemz;
import com.example.prudhvi.graphqlcourse.model.Solutionz;
import com.example.prudhvi.graphqlcourse.model.Userz;
import com.example.prudhvi.graphqlcourse.model.UserzToken;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneOffset;
import java.util.Arrays;

public class GraphqlUtils {

    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHoursMinutes(5, 30);

    public static User mapToGraphql(Userz originalUser) {
        return User.newBuilder()
                .id(originalUser.getId().toString())
                .username(originalUser.getUsername())
                .email(originalUser.getEmail())
                .avatar(originalUser.getAvatar())
                .createDateTime(originalUser.getCreatedAt().atOffset(ZONE_OFFSET))
                .displayName(originalUser.getDisplayName())
                .build();
    }

    public static Problem mapToGraphql(Problemz originalProblem) {
        var createdAt = originalProblem.getCreatedAt().atOffset(ZONE_OFFSET);
        var solutions = originalProblem.getSolutions().stream()
                .map(GraphqlUtils::mapToGraphql)
                .toList();
        var tags = Arrays.stream(originalProblem.getTags().split(",")).toList();

        return Problem.newBuilder()
                .id(originalProblem.getId().toString())
                .createDateTime(createdAt)
                .prettyCreateDateTime(PRETTY_TIME.format(createdAt))
                .title(originalProblem.getTitle())
                .content(originalProblem.getContent())
                .author(mapToGraphql(originalProblem.getCreatedBy()))
                .tags(tags)
                .solutionCount(solutions.size())
                .solutions(solutions)
                .build();
    }

    public static Solution mapToGraphql(Solutionz originalSolution) {
        var createdAt = originalSolution.getCreatedAt().atOffset(ZONE_OFFSET);
        var solutionCategory = SolutionCategory.valueOf(originalSolution.getCategory().toUpperCase());

        return Solution.newBuilder()
                .id(originalSolution.getId().toString())
                .createDateTime(createdAt)
                .prettyCreateDateTime(PRETTY_TIME.format(createdAt))
                .content(originalSolution.getContent())
                .author(mapToGraphql(originalSolution.getCreatedBy()))
                .solutionCategory(solutionCategory)
                .voteAsGoodCount(originalSolution.getVoteGoodCount())
                .voteAsBadCount(originalSolution.getVoteBadCount())
                .build();
    }

    public static UserAuthToken mapToGraphql(UserzToken originalUserToken) {
        return UserAuthToken.newBuilder()
                .authToken(originalUserToken.getAuthToken())
                .expiryTime(originalUserToken.getExpiryAt().atOffset(ZONE_OFFSET))
                .build();
    }
}
