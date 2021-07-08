package cn.ljpc.eduservice.entity.vo.excel;

import lombok.Data;

import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-18 11:07
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children;
}