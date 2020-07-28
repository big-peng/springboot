package com.sippr.demo.common.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ChenXiangpeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String token;
    private LocalDateTime updateTime;
}
