package com.hnu.scw.model;

import javax.persistence.*;

/**
 * @author scw
 * @create 2018-01-24 12:02
 * @desc 审批信息实体
 **/
@Entity
@Table(name = "a_approve")
public class Approve {
    @Id
    @GeneratedValue
    private Long aid;
    //审批人的名字
    private String name;
    //审批是否通过
    private String state;
    //标注
    private String comment;

    /**
     * 一对多的关系的时候，需要让多的一方维护关系
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "leaverbill_id")
    private LeaveBill leaveBill;


    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LeaveBill getLeaveBill() {
        return leaveBill;
    }

    public void setLeaveBill(LeaveBill leaveBill) {
        this.leaveBill = leaveBill;
    }
}
