package com.example.webblog.servies;

import java.text.ParseException;
import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    Boolean remove(Long id);
}