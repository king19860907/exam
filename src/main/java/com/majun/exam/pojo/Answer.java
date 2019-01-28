package com.majun.exam.pojo;

import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ex_answer")
public class Answer {
    @Id
    @Column(name = "row_id")
    @KeySql(useGeneratedKeys = true)
    private Integer rowId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 所做题目id列表
     */
    @Column(name = "question_id_str")
    private String questionIdStr;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取得分
     *
     * @return score - 得分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置得分
     *
     * @param score 得分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取所做题目id列表
     *
     * @return question_id_str - 所做题目id列表
     */
    public String getQuestionIdStr() {
        return questionIdStr;
    }

    /**
     * 设置所做题目id列表
     *
     * @param questionIdStr 所做题目id列表
     */
    public void setQuestionIdStr(String questionIdStr) {
        this.questionIdStr = questionIdStr == null ? null : questionIdStr.trim();
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
}