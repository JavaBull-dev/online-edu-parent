package cn.ljpc.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-18 8:48
 */
@Data
public class SubjectData {

    @ApiModelProperty(value = "一级科目分类")
    @ExcelProperty(index = 0)
    private String oneSubjectTitle;

    @ApiModelProperty(value = "二级科目分类")
    @ExcelProperty(index = 1)
    private String twoSubjectTitle;
}
