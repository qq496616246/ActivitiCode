package com.hnu.scw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author scw
 * @create 2018-01-24 10:25
 * @desc 员工表
 **/
@Entity
@Table(name = "a_employee")
public class Employee implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;
    private String email;
    private String role;

    @JoinColumn(name = "manager_id")
    @ManyToOne(cascade = {CascadeType.REFRESH , CascadeType.MERGE})
    private Employee manager;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},mappedBy = "employee")
    private Set<LeaveBill> leaveBills;

    public Set<LeaveBill> getLeaveBills() {
        return leaveBills;
    }

    public void setLeaveBills(Set<LeaveBill> leaveBills) {
        this.leaveBills = leaveBills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
