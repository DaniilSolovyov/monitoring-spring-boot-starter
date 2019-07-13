package com.gmail.solovyov.daniil.repository;

public interface EventRepository {
    //    Event findByName(String name);
//    Event create(Event)
//    Event save(Event event);
//    int findEventIdByName(String name);

//    int save(String name);
//    void save(String name);
//    Integer createOrUpdate(String name);
    Integer findIdByName(String name);
    Integer create(String name);
}
