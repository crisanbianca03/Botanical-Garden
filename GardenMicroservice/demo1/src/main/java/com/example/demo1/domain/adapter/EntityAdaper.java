package com.example.demo1.domain.adapter;

public interface EntityAdaper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
