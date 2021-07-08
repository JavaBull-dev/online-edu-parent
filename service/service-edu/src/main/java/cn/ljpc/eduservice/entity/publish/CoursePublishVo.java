package cn.ljpc.eduservice.entity.publish;

import lombok.Data;
import lombok.ToString;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-20 11:31
 */
@ToString
@Data
public class CoursePublishVo {
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}