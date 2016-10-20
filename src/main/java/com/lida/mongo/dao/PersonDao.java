package com.lida.mongo.dao;

import com.lida.mongo.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by DuLida on 2016/10/20.
 */
public interface PersonDao extends MongoRepository<Person, ObjectId> {

}
