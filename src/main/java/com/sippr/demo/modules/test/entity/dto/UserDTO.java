package com.sippr.demo.modules.test.entity.dto;

import com.sippr.demo.modules.test.entity.po.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ChenXiangpeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 8679683708414606507L;

    private String username;
    private String password;

    /**
     * {@link User}转{@link UserDTO}
     * @param po
     * @return com.sippr.demo.common.entity.dto.UserDTO
     */
    public static UserDTO of(User po){
        return UserDTO.builder()
                .username(po.getUsername())
                .password(po.getPassword())
                .build();
    }

    public User of(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
