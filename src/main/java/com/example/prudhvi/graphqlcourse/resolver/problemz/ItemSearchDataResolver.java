package com.example.prudhvi.graphqlcourse.resolver.problemz;

import com.example.prudhvi.graphqlcourse.generated.DgsConstants;
import com.example.prudhvi.graphqlcourse.generated.types.SearchItemFilter;
import com.example.prudhvi.graphqlcourse.generated.types.SearchableItem;
import com.example.prudhvi.graphqlcourse.service.ProblemzService;
import com.example.prudhvi.graphqlcourse.service.SolutionzService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@DgsComponent
public class ItemSearchDataResolver {

    private final ProblemzService problemzService;
    private final SolutionzService solutionzService;

    public ItemSearchDataResolver(ProblemzService problemzService, SolutionzService solutionzService) {
        this.problemzService = problemzService;
        this.solutionzService = solutionzService;
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItem(@InputArgument(name = "filter") SearchItemFilter searchItemFilter) {
        var result = new ArrayList<SearchableItem>();
        result.addAll(problemzService.findAllProblemByKeyword(searchItemFilter.getKeyword()));
        result.addAll(solutionzService.findAllSolutionByKeyword(searchItemFilter.getKeyword()));

        result.sort(Comparator.comparing(SearchableItem::getCreateDateTime).reversed());
        return result;
    }

}
