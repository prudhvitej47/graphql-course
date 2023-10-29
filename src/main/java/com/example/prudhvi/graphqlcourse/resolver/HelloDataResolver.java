package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.generated.types.Hello;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.prudhvi.graphqlcourse.datasource.HelloDataSource.HELLO_LIST;

@DgsComponent
public class HelloDataResolver {

    @DgsQuery
    public List<Hello> allHellos() {
        return HELLO_LIST;
    }

    @DgsQuery
    public Hello oneHello() {
        return HELLO_LIST.get(ThreadLocalRandom.current().nextInt(HELLO_LIST.size()));
    }
}
