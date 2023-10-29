package com.example.prudhvi.graphqlcourse.resolver;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.prudhvi.graphqlcourse.datasource.HelloDataSource.HELLO_LIST;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class HelloDataResolverTest {

    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @Test
    public void testQueryResolver_OneHello() {
        @Language("GraphQL") String query = """
                query {
                    oneHello {
                        text
                        randomNumber
                    }
                }
                """;
        String actualText = dgsQueryExecutor.executeAndExtractJsonPath(query, "data.oneHello.text");
        assertNotNull("Text is not null", actualText);
    }

    @Test
    public void testQueryResolver_AllHellos() {
        @Language("GraphQL") String query = """
                query {
                    allHellos {
                        text
                        randomNumber
                    }
                }
                """;
        List<String> actualTextList = dgsQueryExecutor.executeAndExtractJsonPath(query, "data.allHellos[*].text");
        assertEquals("Size is not the same", HELLO_LIST.size(), actualTextList.size());
    }
}
