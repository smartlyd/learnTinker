package com.example.lee.learn.bean;

import java.util.List;

/**
 * Created by Lee on 2017/7/20.
 */

public class TestListBean {

    private int id;
    private String type;
    private Inner inner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<Inner> inners;


    public List<Inner> getInners() {
        return inners;
    }

    public void setInners(List<Inner> inners) {
        this.inners = inners;
    }

    public static class Inner {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
