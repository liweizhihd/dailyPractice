package com.wz.poc.temp;

import com.mongodb.client.result.UpdateResult;
import com.wz.poc.model.po.PetPO;
import com.wz.poc.util.DbHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


/**
 * @author liweizhi
 * @date 2021/1/15
 */
@Slf4j
@SpringBootTest
public class MongoPocTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DbHelper dbHelper;

    @Test
    void elementMatchTest() {
        Criteria criteria4elemMatch = Criteria.where("toUserId").is("user_00000008")
                .and("status").is(1);
        Query query = new Query(
                dbHelper.commonBaseCriteria().and("toUserList").elemMatch(criteria4elemMatch)
        );
        int count = (int) mongoTemplate.count(query, "ks_notify_message");
        log.info("count:{}", count);
    }

    @Test
    void updateNullTest() {
        Query query = new Query(Criteria.where("name").is("xiaobai"));
        Update update = new Update();
        update.set("sequence", 0);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, PetPO.class);
        log.info("updateResult:{}", updateResult);
    }

    @Test
    void getTest() {
        Query query = new Query(Criteria.where("name").is("xiaobai"));
        PetPO one = mongoTemplate.findOne(query, PetPO.class);
        log.info("one:{}", one);
    }

    @Test
    void ignoreFieldsTest() {
        PetPO pet = new PetPO();
        pet.setAge(18);
        pet.setName("xiaobai");
        pet.setTmpString("tempStringAAA");

        PetPO.Message message = new PetPO.Message();
        message.setContent("msgContent text");
        message.setTmpStringInMsg("tempStringB");
        pet.setMessage(message);

        PetPO insert = mongoTemplate.insert(pet);
        log.info("insert:{}", insert);
    }

    @Test
    void readSpecificFieldsTest() {
        Criteria criteria = Criteria.where("name").is("dahuang2");
        Query query = new Query(criteria);
        query.fields().include("name");
        query.fields().include("age");
        List<PetPO> list = mongoTemplate.find(query, PetPO.class);
        log.info("list:{}", list);
    }
}
