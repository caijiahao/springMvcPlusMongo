package dao;

import com.lida.mongo.person.entity.Address;
import com.lida.mongo.person.entity.Person;
import com.lida.mongo.person.mongoDao.PersonMongoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by DuLida on 2016/10/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-context.xml", "classpath:spring/spring-mongo.xml"})
public class MongoTemplateTest {
    private static Logger log = LoggerFactory.getLogger(MongoTemplateTest.class);

    @Resource
    private PersonMongoImpl personMongo;

    @Test
    public void testMongoTemplate() {

        personMongo.insertPerson(new Person("weiwei", 24, new Address("南王", "鑫达路", 10)));
        personMongo.removePerson("name3");
        personMongo.updatePerson();
        log.debug("========================================================");
        System.out.println(personMongo.findAll());
        System.out.println(personMongo.findForRequery("weiwei"));
    }
}
