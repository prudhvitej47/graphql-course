package com.example.prudhvi.graphqlcourse.resolver.problemz;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.*;
import com.example.prudhvi.graphqlcourse.service.UserzService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {

    private final UserzService userzService;

    public UserDataResolver(UserzService userzService) {
        this.userzService = userzService;
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    public User accountInfo(@RequestHeader(name = "authToken") String authToken) {
        var optionalUser = userzService.findUserByAuthToken(authToken);
        return optionalUser.get();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserCreate)
    public UserResponse createUser(@InputArgument("user") UserCreateInput userCreateInput) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserLogin)
    public UserResponse loginUser(@InputArgument("user") UserLoginInput userLoginInput) {
        var generatedToken = userzService.login(userLoginInput.getUsername(), userLoginInput.getPassword());
        var userInfo = accountInfo(generatedToken.getAuthToken());

        return UserResponse.newBuilder()
                .user(userInfo)
                .authToken(generatedToken)
                .build();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserActivation)
    public UserActivationResponse activateUser(@InputArgument("user") UserActivationInput userActivationInput) {
        return null;
    }

}
