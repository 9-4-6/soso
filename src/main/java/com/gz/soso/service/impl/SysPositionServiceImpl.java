package com.gz.soso.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gz.soso.exception.ServiceException;
import com.gz.soso.pojo.constant.CommonConstant;
import com.gz.soso.pojo.dto.PostAddDTO;
import com.gz.soso.pojo.dto.PostListDTO;
import com.gz.soso.pojo.dto.PostUpdateDTO;
import com.gz.soso.pojo.entity.SysDept;
import com.gz.soso.pojo.entity.SysPosition;
import com.gz.soso.pojo.vo.PostListVO;
import com.gz.soso.service.SysPositionService;
import com.gz.soso.mapper.SysPositionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【sys_position(系统岗位表)】的数据库操作Service实现
* @createDate 2025-06-28 22:47:36
*/
@Service
public class SysPositionServiceImpl extends ServiceImpl<SysPositionMapper, SysPosition>
    implements SysPositionService{

    @Override
    public IPage<PostListVO> listPage(PostListDTO dto) {
        Page<SysPosition> page = new Page<>(dto.getCurrent(), dto.getSize());
        // 构建查询条件
        LambdaQueryWrapper<SysPosition> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getPositionName()),
                SysPosition::getPositionName, dto.getPositionName());

        // 执行分页查询
        Page<SysPosition> deptPage = this.page(page, queryWrapper);
        return deptPage.convert(post -> {
            PostListVO vo = new PostListVO();
            BeanUtils.copyProperties(post, vo);
            return vo;
        });
    }

    @Override
    public Long add(PostAddDTO dto) {
        //岗位名称是唯一
        long count = this.count(new LambdaQueryWrapper<SysPosition>().eq(SysPosition::getPositionName, dto.getPositionName()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("岗位名称重复"));
        //新增
        SysPosition sysPosition = BeanUtil.copyProperties(dto, SysPosition.class);
        sysPosition.setCreateTime(DateTime.now());
        //todo 后面补充
        sysPosition.setCreateUser(1L);
        boolean save = this.save(sysPosition);
        Assert.isTrue(save,()->new ServiceException("新增失败"));
        return sysPosition.getId();
    }

    @Override
    public void updatePost(PostUpdateDTO dto) {
        //岗位名称是唯一，排除本身
        long count = this.count(new LambdaQueryWrapper<SysPosition>()
                .eq(SysPosition::getPositionName, dto.getPositionName())
                .ne(SysPosition::getId,dto.getId()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("岗位名称重复"));
        //修改
        SysPosition sysPosition = BeanUtil.copyProperties(dto, SysPosition.class);
        sysPosition.setUpdateTime(DateTime.now());
        //todo 后面补充
        sysPosition.setUpdateUser(1L);
        boolean update = this.updateById(sysPosition);
        Assert.isTrue(update,()->new ServiceException("更新失败"));
    }

    @Override
    public void delete(Long id) {
        boolean remove = this.removeById(id);
        Assert.isTrue(remove,()->new ServiceException("删除失败"));
    }
}




