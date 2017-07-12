package me.duelsol.springmvcseed.entity.demo;

import me.duelsol.springmvcseed.framework.support.BaseEntity;
import me.duelsol.springmvcseed.framework.annotation.Column;
import me.duelsol.springmvcseed.framework.annotation.Table;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/18
 * Time: 18:35
 */
@Table(name = "springmvc_demo")
public class DemoEntity extends BaseEntity {

    @Column(name = "amount")
    private int amount = 0;

    @Column(name = "detail")
    private String detail = "";

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
