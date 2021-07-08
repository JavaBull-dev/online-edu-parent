package cn.ljpc.eduservice.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.vo.excel.OneSubject;
import cn.ljpc.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Jacker
 * @since 2021-06-18
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/add")
    public CommonResult excelRead(MultipartFile file) {
        return eduSubjectService.saveData(file) ? CommonResult.ok() : CommonResult.error();
    }

    @GetMapping("/list")
    public CommonResult list(){
        List<OneSubject> list = eduSubjectService.getAllSubjectList();
        return CommonResult.ok().data("item", list);
    }
}