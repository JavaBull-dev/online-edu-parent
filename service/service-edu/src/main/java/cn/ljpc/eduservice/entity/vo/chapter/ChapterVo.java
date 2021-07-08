package cn.ljpc.eduservice.entity.vo.chapter;

import cn.ljpc.eduservice.entity.vo.VideoVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-19 17:49
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo {
    private String id;
    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
