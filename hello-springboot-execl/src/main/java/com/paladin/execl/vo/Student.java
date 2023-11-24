package com.paladin.execl.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ColumnWidth(20) 这个是设置单元格长度的
 * @ExcelProperty("") 这个是设置表格头部的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(20)
public class Student {

    @ExcelProperty("年级")
    private String gradeName;

    @ExcelProperty("班级")
    private String className;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("性别")
    private String sex;
}