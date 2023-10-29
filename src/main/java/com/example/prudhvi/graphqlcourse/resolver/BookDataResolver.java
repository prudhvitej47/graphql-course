package com.example.prudhvi.graphqlcourse.resolver;

import com.example.prudhvi.graphqlcourse.generated.types.*;
import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.prudhvi.graphqlcourse.datasource.BookDataSource.BOOK_LIST;

@DgsComponent
public class BookDataResolver {

    @DgsData(parentType = "Query", field = "books")
    public List<Book> booksWrittenBy(@InputArgument("author") Optional<String> authorName) {
        return authorName
                .map(s -> BOOK_LIST.stream()
                        .filter(book -> book.getAuthor().getName().equalsIgnoreCase(s))
                        .toList()
                )
                .orElse(BOOK_LIST);

    }

    @SuppressWarnings("unchecked")
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.BooksByReleased)
    public List<Book> getBooksByReleased(DataFetchingEnvironment dataFetchingEnvironment) {
        var releasedMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("released");
        var releasedHistoryInput = ReleaseHistoryInput.newBuilder()
                .year(Integer.valueOf(releasedMap.get("year").toString()))
                .printedEdition(Boolean.valueOf(releasedMap.get("printedEdition").toString()))
                .build();

        return BOOK_LIST.stream()
                .filter(book -> releasedHistoryInput.getYear() == book.getReleased().getYear() && releasedHistoryInput.getPrintedEdition().equals(book.getReleased().getPrintedEdition()))
                .toList();
    }
}
