package cn.ljpc.eduservice.controller;


import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.entity.EduTeacher;
import cn.ljpc.eduservice.entity.vo.TeacherQuery;
import cn.ljpc.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Jacker
 * @since 2021-06-14
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin //跨域
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //获取所有讲师
    @ApiOperation(value = "所有讲师列表")
    @GetMapping(value = "/getAll")
    public CommonResult getAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return CommonResult.ok().data("item", list);
    }

    //删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping(value = "/{id}")
    public CommonResult removeTeacher(@PathVariable("id") String id) { //主键ID是String类型
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    //添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping(value = "/add")
    public CommonResult addTeacher(@RequestBody EduTeacher teacher) {
        teacher.setIsDeleted(false);
        return eduTeacherService.save(teacher) ? CommonResult.ok() : CommonResult.error();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "/pageThacher/{current}/{pagesize}")
    public CommonResult pageListThacher(@PathVariable("current") Long current,
                                        @PathVariable("pagesize") Long pagesize) {
        Page<EduTeacher> teacherPage = new Page<>(current, pagesize);
        IPage<EduTeacher> page = eduTeacherService.page(teacherPage, null);
        return CommonResult.ok()
                .data("total", page.getTotal())
                .data("rows", page.getRecords());
    }

    //带分页的条件查询
    @ApiOperation(value = "带分页的条件查询")
    @PostMapping(value = "/pageThacher/{current}/{pagesize}")
    public CommonResult teacherConditionQuery(@PathVariable("current") Long current,
                                              @PathVariable("pagesize") Long pagesize,
                                              @RequestBody TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(current, pagesize);
        IPage<EduTeacher> page = eduTeacherService.saveTeacher(teacherPage, teacherQuery);

        return CommonResult.ok()
                .data("total", page.getTotal())
                .data("rows", page.getRecords());
    }

    //根据教师id查询
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "/{id}")
    public CommonResult getById(@PathVariable("id") String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return CommonResult.ok().data("item", teacher);
    }

    //修改教师信息
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping
    public CommonResult updateById(@RequestBody EduTeacher teacher) {
        boolean flag = eduTeacherService.updateById(teacher);
        return flag ? CommonResult.ok() : CommonResult.error();
    }
}