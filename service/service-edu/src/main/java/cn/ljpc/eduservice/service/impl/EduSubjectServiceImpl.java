package cn.ljpc.eduservice.service.impl;

import cn.ljpc.eduservice.entity.EduSubject;
import cn.ljpc.eduservice.entity.excel.SubjectData;
import cn.ljpc.eduservice.entity.vo.excel.OneSubject;
import cn.ljpc.eduservice.entity.vo.excel.TwoSubject;
import cn.ljpc.eduservice.listener.ExcelListener;
import cn.ljpc.eduservice.mapper.EduSubjectMapper;
import cn.ljpc.eduservice.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
        implements EduSubjectService {

    @Override
    public boolean saveData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class
                    , new ExcelListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<OneSubject> getAllSubjectList() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        wrapper.orderByAsc("sort", "id");
        List<EduSubject> eduOneSubjects = baseMapper.selectList(wrapper);

        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", "0");
        wrapper2.orderByAsc("sort", "id");
        List<EduSubject> eduTwoSubjects = baseMapper.selectList(wrapper2);

        ArrayList<OneSubject> ret = new ArrayList<>();
        //添加一级分类
        for (EduSubject eduSubject : eduOneSubjects) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);

            ArrayList<TwoSubject> children = new ArrayList<>();
            //添加二级分类
            for (EduSubject eduSubject2 : eduTwoSubjects) {
                if (eduSubject.getId().equals(eduSubject2.getParentId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2, twoSubject);
                    children.add(twoSubject);
                }
            }

            oneSubject.setChildren(children);
            ret.add(oneSubject);
        }

        return ret;
    }
}
