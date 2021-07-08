package cn.ljpc.eduservice.entity.vo;

import lombok.Data;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-19 17:51
 */
@Data
public class VideoVo {

    private String id;

    private String title;

    private Boolean isFree;

    private String videoOriginalName;

    private String videoSourceId;
}
