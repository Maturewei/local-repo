package com.hpu.mapper;

import com.hpu.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    /**
     * 方法名 findStuByUsernameAndPassword
     * @param username 参数1
     * @param password 参数2
     * @return 返回值是对象,是学生类型
     */
    Student findStuByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    /**
     * 查询全部学生
     * 方法名 findAll
     */
    /**
     * 查询全部
     */
    List<Student> findAll();

    /**
    更新
     */
    boolean editById(Student student);

    /**
     删除
     */
    boolean delById(int id);

    /**
     添加
     */
    boolean addStu(Student student);



}
