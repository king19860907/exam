package com.majun.exam.pojo;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ex_question")
public class Question {
    @Id
    @Column(name = "row_id")
    @KeySql(useGeneratedKeys = true)
    private Integer rowId;

    /**
     * 题目内容
     */
    private String description;

    /**
     * 是否删除
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取题目内容
     *
     * @return description - 题目内容
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置题目内容
     *
     * @param description 题目内容
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取是否删除
     *
     * @return delete_flag - 是否删除
     */
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置是否删除
     *
     * @param deleteFlag 是否删除
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}