package cn.ljpc.msm.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-22 19:39
 */
public interface MsmService {

    /**
     * 发送短信
     * @param param 验证码数据
     * @param phone 用户手机号
     * @return
     */
    public boolean send(Map<String, Object> param, String phone);

    boolean send2(HashMap<String, Object> param, String phoneNum);
}
