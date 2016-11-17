package dao;

import com.lida.mongo.dao.PersonDao;
import com.lida.mongo.entity.Address;
import com.lida.mongo.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DuLida on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-context.xml", "classpath:spring/spring-mongo.xml"})
public class PersonDaoTest {

    @Resource
    private PersonDao personDao;


    /*先往数据库中插入10个person*/
    @Test
    public void testMongo() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            persons.add(new Person("name" + i, i, new Address("石家庄", "裕华路", i)));
        }
        personDao.save(persons);
    }

    @Test
    public void findMongo() {
        System.out.println(personDao.findByAge(2, 8, "name6"));

    }

}
