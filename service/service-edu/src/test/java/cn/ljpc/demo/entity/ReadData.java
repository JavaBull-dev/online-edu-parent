package cn.ljpc.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 22:17
 */
@Data
@ToString
public class ReadData {

    @ExcelProperty(index = 0)//在execel中的列号
    private int sid;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private Integer age;
}
