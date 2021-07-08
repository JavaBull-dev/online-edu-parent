package cn.ljpc.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-22 20:07
 */

@Data
@ApiModel(value = "登录对象", description = "登录对象")
public class LoginVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}