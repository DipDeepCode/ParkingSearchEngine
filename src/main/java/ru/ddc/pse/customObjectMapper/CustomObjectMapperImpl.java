package ru.ddc.pse.customObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomObjectMapperImpl implements CustomObjectMapper {
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomObjectMapperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <S, T> List<T> mapObjectListToModelList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(s -> objectMapper.convertValue(s, targetClass))
                .collect(Collectors.toList());
    }
}
