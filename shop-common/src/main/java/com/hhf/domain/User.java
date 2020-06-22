package com.hhf.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户
 */
@Entity(name = "shop_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 2613439157031959833L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;//主键    
    private String username;//用户名
    private String password;//密码
    private String telephone;//手机号
}

