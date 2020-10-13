package com.sippr.demo.modules.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ChenXiangpeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "collection")
public class TestPo {
    private String username;
    private Integer status;
    @Field("is_deleted")
    private Boolean isDeleted;
}
