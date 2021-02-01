package com.wz.poc.model.po;

import com.wz.poc.model.annotation.EntityLogicId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * @author liweizhi
 * @date 2020/9/17
 */
@Data
@Document("pet")
public class PetPO {
    @Id
    protected String id;


    @EntityLogicId
    private String petNo;

    private String name;
    private Integer age;
    private Integer sequence;
    private Boolean deleteFlag;
    private Message message;
    private Long createTime;
    private List<String> testList;
    private Set<String> testSet;

    @Transient
    private String tmpString;

    public PetPO(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public PetPO() {
    }

    @Data
    public static class Message {
        /**
         * 消息内容
         */
        private String content;

        /**
         * 消息内容的类型, 1:文字,2:图片,3:表情,4:语音
         */
        private Integer contentType;

        /**
         * 如果是时IM聊天,记录一个sessionNo
         */
        private String sessionNo;

        @Transient
        private String tmpStringInMsg;
    }
}
