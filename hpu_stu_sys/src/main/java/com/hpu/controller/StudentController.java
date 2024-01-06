package com.hpu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpu.mapper.StudentMapper;
import com.hpu.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin         // 允许跨域请求
@RestController // 加上RestController,返回的是JSON对象
public class StudentController {

    /**
     * 注入Mapper对象即可使用mapper操作数据库
     * ps: @Autowired加上,此处会有红色报错,不用管
     */
    @Autowired
    private StudentMapper studentMapper;


    /**
     * http://localhost:8080/login?usrename=xx&password=xx
     */
    @RequestMapping("/login")
    public Student login(@RequestBody Student student) {
        // sout 快捷键输出语句
        System.out.println("username = " + student.getUsername());
        System.out.println("password = " + student.getPassword());

        // 对象调用方法,传入实参,接收返回值
        Student stu = studentMapper.findStuByUsernameAndPassword(student.getUsername(), student.getPassword());
        System.out.println(stu );
        return stu;
    }

    /*
    接受前端查询请求
    */
    @RequestMapping("/stu/list")   //前后端路径要一致
    public PageInfo<Student> findAll(int currentPage, int pageSize){
        System.out.println("currentPage = "+ currentPage);
        System.out.println("pageSize = "+ pageSize);
        /*
        //在查询全部前利用pageHelper框架实现自动分页
        * PageHelper 工具会自动帮我们拼接limit语句，我们不需要改sql
        */
        PageHelper.startPage(currentPage,pageSize);

        List<Student> list = studentMapper.findAll( );
        System.out.println(list );

        //收集分页信息
        PageInfo<Student> info = new PageInfo<>(list);
        return info;
    }


    /*
    接受更新学生数据时的请求
     */
    @RequestMapping("/stu/edit")
    public boolean editById(@RequestBody Student student){
        System.out.println("更新的对象"+student);
        boolean b = studentMapper.editById(student);
        return b;
    }

    /**
     * 接受删除学生数据时的请求
     */
    @RequestMapping("/stu/del")
    public boolean delById(int id){
        System.out.println("删除的id" + id);
        // 调用mapper完成删除
        boolean b = studentMapper.delById(id);
        return b;
    }

    /**
     * 接受删除学生数据时的请求
     */
    @RequestMapping("/stu/add")
    public boolean addStu(@RequestBody Student student){
        System.out.println("添加的数据" + student );
        // 调用mapper完成添加
        boolean b = studentMapper.addStu(student);
        return b;
    }
}