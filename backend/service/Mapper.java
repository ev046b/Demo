package com.example.backend.service;

import org.springframework.data.domain.Page;

import java.util.Collection;

public interface Mapper<E, D, R> {

    Collection<D> toResponseCollection(Collection<E> collection);

    Page<D> toResponsePage(Page<E> page);

    E toEntity(R requestDto);

    D toResponseDto(E entity);

    E validation(R requestDto);

    D save(R requestDto);

}
