package com.paladin.execl.controller;

import com.paladin.execl.service.StudentService;
import com.paladin.execl.util.EasyExcelUtil;
import com.paladin.execl.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hello")
    public String hello(){
        return "SUCCESS";
    }

    @GetMapping("/createExcel")
    public void createExcel(HttpServletResponse response) throws Exception{
        List<Student> studentData = studentService.getStudentData();
        String fileName  =  "学生列表";
        /**
         * 第一个参数：HttpServletResponse
         * 第二个参数：文件名称
         * 第三个参数：数据集
         * 第四个参数：数据集实体class对象
         */
        EasyExcelUtil.createExcel(response,fileName,studentData, Student.class);
    }
}