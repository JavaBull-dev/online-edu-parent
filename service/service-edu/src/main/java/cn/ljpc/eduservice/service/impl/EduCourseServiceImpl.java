package cn.ljpc.eduservice.service.impl;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.eduservice.client.VodClient;
import cn.ljpc.eduservice.entity.EduChapter;
import cn.ljpc.eduservice.entity.EduCourse;
import cn.ljpc.eduservice.entity.EduCourseDescription;
import cn.ljpc.eduservice.entity.EduVideo;
import cn.ljpc.eduservice.entity.frontvo.CourseFrontVo;
import cn.ljpc.eduservice.entity.frontvo.CourseWebVo;
import cn.ljpc.eduservice.entity.publish.CoursePublishVo;
import cn.ljpc.eduservice.entity.vo.course.CourseInfoForm;
import cn.ljpc.eduservice.entity.vo.course.CourseQuery;
import cn.ljpc.eduservice.mapper.EduCourseMapper;
import cn.ljpc.eduservice.service.EduChapterService;
import cn.ljpc.eduservice.service.EduCourseDescriptionService;
import cn.ljpc.eduservice.service.EduCourseService;
import cn.ljpc.eduservice.service.EduVideoService;
import cn.ljpc.servicebase.exception.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
        implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private VodClient vodClient;

    /**
     * 保存课程信息
     * @param courseInfoForm
     * @return
     */
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        eduCourse.setIsDeleted(0);
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        //影响的行数
        boolean save1 = this.save(eduCourse);
        if (!save1) {
            throw new MyException(20001, "添加或更新课程信息失败");
        }

        String cid = eduCourse.getId();
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoForm.getDescription());
        description.setId(cid);
        boolean save = eduCourseDescriptionService.save(description);
        if (!save) {
            throw new MyException(20001, "添加或更新课程描述信息失败");
        }
        return cid;
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String courseId) {
        CourseInfoForm courseInfo = new CourseInfoForm();
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse, courseInfo);

        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        courseInfo.setDescription(description.getDescription());
        return courseInfo;
    }

    /**
     * 更新课程信息
     * @param courseInfoForm
     */
    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if (!resultCourseInfo) {
            throw new MyException(20001, "课程信息保存失败");
        }
        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = eduCourseDescriptionService.updateById(courseDescription);
        if (!resultDescription) {
            throw new MyException(20001, "课程详情信息保存失败");
        }
    }

    /**
     * 获取课程发布信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo getCoursePublishInfo(String id) {
        return this.baseMapper.getCourseInfoById(id);
    }

    //获取课程的发布信息
    //性能低下
//    @Override
//    public CoursePublishVo getCoursePublishInfo(String id) {
//        EduCourse course = this.getById(id);
//        String teacherId = course.getTeacherId();
//        String subjectId = course.getSubjectId();
//        String subjectParentId = course.getSubjectParentId();
//        EduSubject subject = eduSubjectService.getById(subjectId);
//        EduSubject subjectParent = eduSubjectService.getById(subjectParentId);
//
//        EduTeacher teacher = eduTeacherService.getById(teacherId);
//        CoursePublishVo ret = new CoursePublishVo();
//        ret.setCover(course.getCover());
//        ret.setLessonNum(course.getLessonNum());
//        ret.setPrice(course.getPrice().toString());
//        ret.setTitle(course.getTitle());
//        ret.setTeacherName(teacher.getName());
//        ret.setSubjectLevelOne(subjectParent.getTitle());
//        ret.setSubjectLevelTwo(subject.getTitle());
//        return ret;
//    }

    /**
     * 发布课程
     * @param id
     * @return
     */
    @Override
    public boolean publishCourse(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus(EduCourse.COURSE_NORMAL);
        return this.updateById(course);
    }

    /**
     * 分页查询课程信息
     * @param pageParam
     * @param courseQuery
     */
    @Override
    public void pageCourseInfo(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    /**
     * 删除课程，删除课程下的章节，删除章节下的小节，删除课程描述信息
     * @param id
     */
    @Override
    public void removeCourse(String id) {
        //删除阿里云视频
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id", id);
        eduVideoQueryWrapper.select("video_source_id");//选择返回video_source_id
        List<EduVideo> eduVideos = eduVideoService.list(eduVideoQueryWrapper);
        List<String> videoList = new ArrayList<>();
        for (EduVideo video: eduVideos){
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoList.add(videoSourceId);
            }
        }
        if (videoList.size()>0){
            CommonResult commonResult = vodClient.removeVideoSources(videoList);
            if (!commonResult.getSuccess()){
                throw new MyException(20001, commonResult.getMessage());
            }
        }

        // 删除所有小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", id);
        boolean remove = eduVideoService.remove(videoQueryWrapper);
        if (!remove) {
            throw new MyException(20001, "删除课程失败");
        }

        //删除所有章节
        boolean b = this.removeById(id);
        if (!b) {
            throw new MyException(20001, "删除课程失败");
        }

        // 删除课程描述信息
        boolean b1 = eduCourseDescriptionService.removeById(id);
        if (!b1){
            throw new MyException(20001, "删除课程失败");
        }

        // 删除所有课程
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        boolean remove1 = eduChapterService.remove(wrapper);
        if (!remove1) {
            throw new MyException(20001, "删除课程失败");
        }
    }

    //1 条件查询带分页查询课程
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam,wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    //根据课程id，编写sql语句查询课程信息
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}