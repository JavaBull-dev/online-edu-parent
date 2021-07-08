package cn.ljpc.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 22:08
 */
@Data
@ToString
@NoArgsConstructor
public class DemoData {

    @ExcelProperty("学生编号")
    private int sno;

    @ExcelProperty("学生姓名")
    private String sname;

    @ExcelProperty("学生年龄")
    private Integer age;


}
