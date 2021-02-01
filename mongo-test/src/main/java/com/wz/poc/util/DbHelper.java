package com.wz.poc.util;

import com.wz.poc.model.annotation.EntityLogicId;
import com.wz.poc.model.annotation.EntityLogicKey;
import com.wz.poc.model.annotation.FieldInMatch;
import com.wz.poc.model.annotation.StringFieldLikeMatch;
import com.wz.poc.model.bo.GroupCount;
import com.wz.poc.model.po.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author liweizhi
 * @date 2020/11/11
 */
@Component
public class DbHelper {

    @Autowired
    protected MongoTemplate mongoTemplate;


    public static final String FIELD_DELETE = "isDelete";
    public static final String FIELD_ID = "id";

    public <T extends BaseEntity> T findOne(Class<T> clazz, String fieldName, String value) {
        return mongoTemplate.findOne(new Query(commonBaseCriteria().and(fieldName).is(value)), clazz);
    }

    public <T extends BaseEntity> List<T> find(T entity, Criteria criteria) {
        Criteria criteriaWithExample = getQueryExample(entity);
        Query query = new Query(criteriaWithExample);
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        return (List<T>) mongoTemplate.find(query, entity.getClass());
    }

    public <T extends BaseEntity> Page<T> find(T entity, Criteria criteria, Pageable pageable, Sort sort) {
        Criteria criteriaWithExample = getQueryExample(entity);
        Query query = new Query(criteriaWithExample);
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        long totalCount = mongoTemplate.count(query, entity.getClass());
        query.with(pageable);
        if (Objects.nonNull(sort)) {
            query.with(sort);
        }
        List<T> list = (List<T>) mongoTemplate.find(query, entity.getClass());
        return new PageImpl<>(list, pageable, totalCount);
    }

    /**
     * 批量删除
     *
     * @param entityClass 实体类class
     * @param fieldName   字段名
     * @param values      值
     * @return 删除条数
     */
    public long deleteBatch(Class<? extends BaseEntity> entityClass, String fieldName, Collection<String> values) {
        Query query = new Query(Criteria.where(fieldName).in(values));
        Update update = Update.update(FIELD_DELETE, true);
        return mongoTemplate.updateMulti(query, update, entityClass).getModifiedCount();
    }

    /**
     * 更新
     *
     * @param entity 实体类
     * @return 更新条数
     */
    public <T extends BaseEntity> long update(T entity) {
        setBaseFieldValue(entity, false);
        Update update = new Update();
        Criteria criteria = commonBaseCriteria();
        AtomicBoolean setQueryField = new AtomicBoolean(false);
        ReflectionUtils.doWithFields(entity.getClass(), field -> {
            field.setAccessible(true);
            Object value = field.get(entity);
            // 设置查询条件
            if (value != null && (field.isAnnotationPresent(EntityLogicKey.class) || field.isAnnotationPresent(EntityLogicId.class))) {
                criteria.and(field.getName()).is(value);
                setQueryField.set(true);
                return;
            }
            // 排除指定条件: 值为空,id字段,静态字段
            if (value == null || FIELD_ID.equals(field.getName()) || Modifier.isStatic(field.getModifiers())) {
                return;
            }
            // 设置更新的字段
            update.set(field.getName(), value);
        });

        // 防御,避免查询条件为空 或 更新字段为空
        if (update.getUpdateObject().isEmpty() || !setQueryField.get()) {
            return 0;
        }
        Query query = new Query(criteria);
        return mongoTemplate.updateMulti(query, update, entity.getClass()).getModifiedCount();
    }

    /**
     * 更新共有的基础字段
     *
     * @param entity   实体
     * @param isInsert true表示新增,false表示编辑
     */
    public void setBaseFieldValue(BaseEntity entity, boolean isInsert) {

        long nowTime = System.currentTimeMillis();
        entity.setGmtModified(nowTime);
        if (isInsert) {
            entity.setGmtCreate(nowTime);
            entity.setIsDelete(false);
            entity.setEnabled(true);
        }
    }

    /**
     * @return 通用的筛选条件 {"enabled":true,"isDelete":false}
     */
    public Criteria commonBaseCriteria() {
        return Criteria.where("enabled").is(true).and("isDelete").is(false);
    }

    public <T extends BaseEntity> Criteria getQueryExample(T entity) {
        Criteria criteria = new Criteria();
        ReflectionUtils.doWithFields(entity.getClass(), field -> {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) {
                return;
            }
            Object value = field.get(entity);
            if (value == null) {
                return;
            }
            if (field.isAnnotationPresent(StringFieldLikeMatch.class)) {
                // 模糊匹配
                criteria.and(field.getName()).regex("^.*" + value + ".*$");
            } else if (field.isAnnotationPresent(FieldInMatch.class)) {
                // 模糊匹配
                criteria.and(field.getName()).in((Collection<?>) value);
            } else {
                // 精准匹配
                criteria.and(field.getName()).is(value);
            }
        });
        return criteria;
    }

    private boolean isLogicKeyOrId(Field field) {
        return field.isAnnotationPresent(EntityLogicKey.class) || field.isAnnotationPresent(EntityLogicId.class);
    }

    /**
     * 分组查询
     *
     * @param groupId   按照哪个字段分组(表示用户ID的字段)
     * @param values    分组字段的值,作为筛选条件
     * @param inputType 查询的哪个Entity
     * @return Map<groupId, count>
     */
    public Map<String, Integer> count(String groupId, Collection<String> values, Class<?> inputType) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(commonBaseCriteria().and(groupId).in(values)),
                Aggregation.group(groupId).count().as("count"),
                Aggregation.project("count").and("group").previousOperation()
        );
        AggregationResults<GroupCount> aggRet = mongoTemplate.aggregate(aggregation, inputType, GroupCount.class);
        return aggRet.getMappedResults().stream().collect(Collectors.toMap(GroupCount::getGroup, GroupCount::getCount));
    }
}
