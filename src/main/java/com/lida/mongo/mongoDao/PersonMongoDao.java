package com.lida.mongo.mongoDao;

import com.lida.mongo.entity.Person;

import java.util.List;

/**
 * Created by DuLida on 2016/10/21.
 */
public interface PersonMongoDao {
    public List<Person> findAll();
    public void insertPerson(Person user);
    public void removePerson(String userName);
    public void updatePerson();
    public List<Person> findForRequery(String userName);
}
