package com.majun.exam.pojo;

import javax.persistence.*;

@Table(name = "ex_user")
public class User {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rowId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * @return row_id
     */
    public Integer getRowId() {
        return rowId;
    }

    /**
     * @param rowId
     */
    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}