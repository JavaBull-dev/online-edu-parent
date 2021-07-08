package cn.ljpc.educms.service.impl;

import cn.ljpc.educms.entity.CrmBanner;
import cn.ljpc.educms.mapper.CrmBannerMapper;
import cn.ljpc.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Jacker
 * @since 2021-06-21
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //redis 缓存
    @Cacheable(value = "banner", key = "'getAllBanner'")
    @Override
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        return this.list(queryWrapper);
    }
}
