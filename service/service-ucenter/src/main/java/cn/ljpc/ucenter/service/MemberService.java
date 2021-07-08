package cn.ljpc.ucenter.service;

import cn.ljpc.ucenter.entity.Member;
import cn.ljpc.ucenter.entity.vo.LoginVo;
import cn.ljpc.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-22
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Member getOpenIdMember(String openid);
}
