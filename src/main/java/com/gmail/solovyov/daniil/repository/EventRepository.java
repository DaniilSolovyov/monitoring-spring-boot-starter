package com.gmail.solovyov.daniil.repository;

public interface EventRepository {
    Integer findIdByName(String name);

    Integer create(String name);
}
