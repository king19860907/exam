package com.majun.exam.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ex_option")
public class Option {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rowId;

    /**
     * 题目id
     */
    @Column(name = "question_id")
    private Integer questionId;

    /**
     * 是否删除
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    /**
     * 是否为答案
     */
    private Boolean answer;

    @Column(name = "create_time")
    private Date createTime;

    private String description;

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
     * 获取题目id
     *
     * @return question_id - 题目id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目id
     *
     * @param questionId 题目id
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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
     * 获取是否为答案
     *
     * @return answer - 是否为答案
     */
    public Boolean getAnswer() {
        return answer;
    }

    /**
     * 设置是否为答案
     *
     * @param answer 是否为答案
     */
    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}