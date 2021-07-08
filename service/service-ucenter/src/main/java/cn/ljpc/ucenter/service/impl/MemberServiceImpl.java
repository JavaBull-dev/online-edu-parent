package cn.ljpc.ucenter.service.impl;

import cn.ljpc.commonutils.JwtUtils;
import cn.ljpc.servicebase.exception.MyException;
import cn.ljpc.ucenter.entity.Member;
import cn.ljpc.ucenter.entity.vo.LoginVo;
import cn.ljpc.ucenter.entity.vo.RegisterVo;
import cn.ljpc.ucenter.mapper.MemberMapper;
import cn.ljpc.ucenter.service.MemberService;
import cn.ljpc.ucenter.util.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 会员注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if (StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new MyException(20001, "error");
        }

        //校验验证码
        String rightCode = redisTemplate.opsForValue().get(mobile);//mobile: code
        if (!code.equals(rightCode)) {
            throw new MyException(20001, "error");
        }

        //查询数据库中是否存在相同的手机号码，会员的手机号码需要唯一
        Integer count = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if (count > 0) {
            throw new MyException(20001, "error");
        }

        //注册用户
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }

    /**
     * 会员登入
     *
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //验证参数
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new MyException(20001, "error");
        }

        //查询用户，用户的密码被加密
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Member one = this.getOne(wrapper);
        if (one == null) {
            throw new MyException(20001, "error");
        }

        //验证密码是否正确
        if (!MD5.encrypt(password).equals(one.getPassword())){
            throw new MyException(20001, "error");
        }

        //校验是否被禁用
        if(one.getIsDisabled()) {
            throw new MyException(20001,"error");
        }

        //使用JWT生成token字符串
        String jwtToken = JwtUtils.getJwtToken(one.getId(), one.getNickname());
        return jwtToken;
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        Member member = this.getOne(wrapper);
        return member;
    }
}
