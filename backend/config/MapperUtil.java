package com.example.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MapperUtil {
    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(e -> converter.apply(e)).collect(Collectors.toList());
    }

    public static <R, E> Collection<R> convertCollection(Collection<E> collection, Function<E, R> converter) {
        return collection.stream().map(e -> converter.apply(e)).collect(Collectors.toList());
    }

    public static <R, E> Page<R> convertPages(Page<E> pages, Function<E, R> converter) {
        return pages.map(e -> converter.apply(e));
    }
}
