package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.Hello;
import com.example.prudhvi.graphqlcourse.generated.types.HelloInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

import static com.example.prudhvi.graphqlcourse.datasource.HelloDataSource.HELLO_LIST;

@DgsComponent
public class HelloDataMutation {

    // @DgsMutation
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.AddHello)
    public int addHello(@InputArgument("helloInput") HelloInput helloInput) {
        var hello = Hello.newBuilder()
                .text(helloInput.getText())
                .randomNumber(helloInput.getNumber())
                .build();

        HELLO_LIST.add(hello);

        return HELLO_LIST.size();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ReplaceHello)
    public List<Hello> replaceHello(@InputArgument("helloInput") HelloInput helloInput) {
        HELLO_LIST.stream()
                .filter(hello -> helloInput.getNumber() == hello.getRandomNumber())
                .forEach(hello -> hello.setText(helloInput.getText()));

        return HELLO_LIST;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.DeleteHello)
    public int deleteHello(@InputArgument("number") int number) {
        HELLO_LIST.removeIf(hello -> number == hello.getRandomNumber());
        return HELLO_LIST.size();
    }
}
