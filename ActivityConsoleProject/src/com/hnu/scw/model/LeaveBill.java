package com.hnu.scw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author scw
 * @create 2018-01-24 10:33
 * @desc 请假单表
 **/
@Entity
@Table(name = "a_leaveBill")
public class LeaveBill implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    // 请假天数
    private Integer days;
    // 请假内容
    private String content;
    // 请假时间
    private Date leaveDate = new Date();
    // 备注
    private String remark;

    /**
     * 一对多的关系的时候，多的一方需要维护关系
     */
    // 请假人
    @JoinColumn(name = "emplyoee_id")
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH , CascadeType.MERGE})
    private Employee employee;

    /**
     * 一对多的关系的时候，多的一方维护关系，一的一方不需要进行维护关系
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH , CascadeType.MERGE},mappedBy = "leaveBill")
    private Set<Approve> approves;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Approve> getApproves() {
        return approves;
    }

    public void setApproves(Set<Approve> approves) {
        this.approves = approves;
    }
}
