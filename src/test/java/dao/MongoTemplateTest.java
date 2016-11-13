package dao;

import com.lida.mongo.dao.PersonDao;
import com.lida.mongo.entity.Address;
import com.lida.mongo.entity.Person;
import com.lida.mongo.mongoDao.PersonMongoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by DuLida on 2016/10/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-context.xml","classpath:spring/spring-mongo.xml"})
public class MongoTemplateTest {

    @Resource
    private PersonMongoImpl personMongo;

    @Test
    public void testMongoTemplate() {

        personMongo.insertPerson(new Person("weiwei",24,new Address("南王","鑫达路",10)));
        personMongo.removePerson("name3");
        personMongo.updatePerson();
        System.out.println(personMongo.findAll());
        System.out.println(personMongo.findForRequery("weiwei"));
    }
}
