package ru.ddc.pse.services;

import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.model.SearchRequest;
import com.manticoresearch.client.model.SearchResponse;
import com.manticoresearch.client.model.SearchResponseHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ddc.pse.customObjectMapper.CustomObjectMapperImpl;
import ru.ddc.pse.exceptions.PseException;
import ru.ddc.pse.dto.HitDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final CustomObjectMapperImpl objectMapper;
    private final SearchApi searchApi;

    @Autowired
    public SearchServiceImpl(CustomObjectMapperImpl objectMapper, SearchApi searchApi) {
        this.objectMapper = objectMapper;
        this.searchApi = searchApi;
    }

    @Value("${manticore.index_name}")
    private String manticoreIndexName;
    @Value("${manticore.limit_of_items}")
    private int limitOfItemsInTheResponseList;
    @Value("${manticore.query_type}")
    private String manticoreQueryType;
    @Value("${manticore.wildcard_operator.left}")
    private String leftWildcardOperator;
    @Value("${manticore.wildcard_operator.right}")
    private String rightWildcardOperator;
    @Value("${manticore.column_name}")
    private String manticoreColumnName;
    @Override
    public List<String> getListOfMatches(String queryString) throws PseException {
        SearchRequest searchRequest = getSearchRequest(queryString);
        SearchResponse searchResponse;
        try {
            searchResponse = searchApi.search(searchRequest);
        } catch (ApiException e) {
            throw new PseException(e.getMessage());
        }

        List<HitDto> hitDtoList = mapResponseToHitDto(searchResponse);

        return parseHitDtoListToStringList(hitDtoList);
    }

    private SearchRequest getSearchRequest(String queryString) {
        Map<String, String> query = new HashMap<>();
        query.put(manticoreQueryType, leftWildcardOperator + queryString + rightWildcardOperator);
        return new SearchRequest()
                .index(manticoreIndexName)
                .limit(limitOfItemsInTheResponseList)
                .query(query);
    }

    private List<HitDto> mapResponseToHitDto(SearchResponse searchResponse) {
        SearchResponseHits hits = searchResponse.getHits();
        assert hits != null;
        List<Object> objectList= hits.getHits();
        assert objectList != null;
        return objectMapper.mapObjectListToModelList(objectList, HitDto.class);
    }

    private List<String> parseHitDtoListToStringList(List<HitDto> hitDtoList) {
        return hitDtoList.stream()
                .map(hitDto -> hitDto.getSource().get(manticoreColumnName))
                .collect(Collectors.toList());
    }
}
