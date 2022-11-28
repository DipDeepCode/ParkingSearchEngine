package ru.ddc.pse.customObjectMapper;

import java.util.List;

public interface CustomObjectMapper {
    <S, T> List<T> mapObjectListToModelList(List<S> source, Class<T> targetClass);
}
