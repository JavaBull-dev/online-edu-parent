package cn.ljpc.eduservice.listener;

import cn.ljpc.eduservice.entity.EduSubject;
import cn.ljpc.eduservice.entity.excel.SubjectData;
import cn.ljpc.eduservice.service.EduSubjectService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-18 8:47
 */
@AllArgsConstructor
public class ExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    //读取行行信息
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        String oneSubjectTitle = subjectData.getOneSubjectTitle();
        String twoSubjectTitle = subjectData.getTwoSubjectTitle();

        // 查看一级学科分类是否在数据库中出现
        EduSubject eduSubject = existOneSubject(eduSubjectService, oneSubjectTitle);
        if (eduSubject == null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(oneSubjectTitle);
            eduSubject.setSort(0);
            eduSubjectService.save(eduSubject);
        }

        // 查看二级学科分类是否在数据库中出现
        String pid = eduSubject.getId();
        System.out.println(pid);
        EduSubject subject = existTwoSubject(eduSubjectService, twoSubjectTitle, pid);
        if (subject == null) {
            subject = new EduSubject();
            subject.setParentId(pid);
            subject.setTitle(twoSubjectTitle);
            subject.setSort(0);
            eduSubjectService.save(subject);
        }
    }

    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        wrapper.eq("title", name);
        return eduSubjectService.getOne(wrapper);
    }

    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", pid);
        wrapper.eq("title", name);
        return eduSubjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
