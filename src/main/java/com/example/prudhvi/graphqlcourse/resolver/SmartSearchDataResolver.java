package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.SmartSearchResult;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.prudhvi.graphqlcourse.datasource.BookDataSource.BOOK_LIST;
import static com.example.prudhvi.graphqlcourse.datasource.HelloDataSource.HELLO_LIST;

@DgsComponent
public class SmartSearchDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearch(@InputArgument("keyword") Optional<String> keyword) {
        var smartSearchList = new ArrayList<SmartSearchResult>();

        if (keyword.isEmpty()) {
            smartSearchList.addAll(HELLO_LIST);
            smartSearchList.addAll(BOOK_LIST);
        } else {
            var keywordString = keyword.get();

            HELLO_LIST.stream()
                    .filter(hello -> StringUtils.containsIgnoreCase(hello.getText(), keywordString))
                    .forEach(smartSearchList::add);

            BOOK_LIST.stream()
                    .filter(book -> StringUtils.containsIgnoreCase(book.getTitle(), keywordString))
                    .forEach(smartSearchList::add);
        }

        return smartSearchList;
    }
}
