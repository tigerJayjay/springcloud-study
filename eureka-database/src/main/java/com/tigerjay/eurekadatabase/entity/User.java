package com.tigerjay.eurekadatabase.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author tigerJay
 * @since 2019-09-17
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份标识:0代表普通用户，1代表管理员
     */
    private String identity;


}
