package ru.ddc.pse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.pse.exceptions.PseException;
import ru.ddc.pse.services.SearchService;

import java.util.List;

@RestController
@RequestMapping("/pse")
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/getListOfMatches")
    public List<String> getListOfMatches(@RequestParam String queryString) throws PseException {
        return searchService.getListOfMatches(queryString);
    }
}
