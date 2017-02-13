package com.duelsol.springmvcseed.entity.demo;

import com.duelsol.springmvcseed.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * Author: 冯奕骅
 * Date: 14/10/18
 * Time: 18:35
 */
public class DemoEntity extends BaseEntity {

    private int amount = 0;
    private String detail = "";

    @Override
    public String getTableName() {
        return "footprint_demo";
    }

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
