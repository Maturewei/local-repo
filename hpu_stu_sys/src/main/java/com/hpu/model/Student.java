package com.hpu.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/* @desc 这是一个类
 * 定义一些属性,这些属性要和数据库tb_stu对应
 * 因为数据库查询出的数据,要用这个类的对象封装!
 */
public class Student {

    // private是修饰符,私有的意思
    private int id;
    // int是整型,String是字符串,double浮点型
    private String username;
    private String password;
    private int  age;
    private int sex;
    private double  score;
    // java.util.Date的不要导错包
    // Date也是数据类型,代表日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    // alt+insert或者右键Generate选择getter and setter方法和toString
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", score=" + score +
                ", birthday=" + birthday +
                '}';
    }
}
