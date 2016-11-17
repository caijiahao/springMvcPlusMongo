package com.lida.mongo.mongoDao;

import com.lida.mongo.entity.Person;

import java.util.List;

/**
 * Created by DuLida on 2016/10/21.
 */
public interface PersonMongoDao {
    List<Person> findAll();

    void insertPerson(Person user);

    void removePerson(String userName);

    void updatePerson();

    List<Person> findForRequery(String userName);
}
