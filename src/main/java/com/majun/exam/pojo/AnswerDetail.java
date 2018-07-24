package com.majun.exam.pojo;

import javax.persistence.*;

@Table(name = "ex_answer_detail")
public class AnswerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 回答id
     */
    @Column(name = "answer_id")
    private Integer answerId;

    /**
     * 题目id
     */
    @Column(name = "question_id")
    private Integer questionId;

    /**
     * 所选答案id
     */
    @Column(name = "choose_answer_id")
    private Integer chooseAnswerId;

    /**
     * 正确答案id
     */
    @Column(name = "right_answer_id")
    private Integer rightAnswerId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取回答id
     *
     * @return answer_id - 回答id
     */
    public Integer getAnswerId() {
        return answerId;
    }

    /**
     * 设置回答id
     *
     * @param answerId 回答id
     */
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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
     * 获取所选答案id
     *
     * @return choose_answer_id - 所选答案id
     */
    public Integer getChooseAnswerId() {
        return chooseAnswerId;
    }

    /**
     * 设置所选答案id
     *
     * @param chooseAnswerId 所选答案id
     */
    public void setChooseAnswerId(Integer chooseAnswerId) {
        this.chooseAnswerId = chooseAnswerId;
    }

    /**
     * 获取正确答案id
     *
     * @return right_answer_id - 正确答案id
     */
    public Integer getRightAnswerId() {
        return rightAnswerId;
    }

    /**
     * 设置正确答案id
     *
     * @param rightAnswerId 正确答案id
     */
    public void setRightAnswerId(Integer rightAnswerId) {
        this.rightAnswerId = rightAnswerId;
    }
}