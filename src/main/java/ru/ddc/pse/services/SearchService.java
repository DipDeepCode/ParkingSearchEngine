package ru.ddc.pse.services;

import ru.ddc.pse.exceptions.PseException;

import java.util.List;

public interface SearchService {

    List<String> getListOfMatches(String queryString) throws PseException;
}
