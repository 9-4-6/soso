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
import com.gz.soso.pojo.dto.UserAddDTO;
import com.gz.soso.pojo.dto.UserListDTO;
import com.gz.soso.pojo.dto.UserUpdateDTO;
import com.gz.soso.pojo.entity.SysUser;
import com.gz.soso.pojo.vo.RoleListVO;
import com.gz.soso.service.SysUserService;
import com.gz.soso.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【sys_user(系统用户表)】的数据库操作Service实现
* @createDate 2025-06-28 22:45:35
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public IPage<RoleListVO> listPage(UserListDTO dto) {
        Page<SysUser> page = new Page<>(dto.getCurrent(), dto.getSize());
        // 构建查询条件
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getName()),
                SysUser::getName, dto.getName());

        // 执行分页查询
        Page<SysUser> deptPage = this.page(page, queryWrapper);
        return deptPage.convert(user -> {
            RoleListVO vo = new RoleListVO();
            BeanUtils.copyProperties(user, vo);
            return vo;
        });
    }

    @Override
    public Long add(UserAddDTO dto) {
        //用户名称是唯一
        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getName, dto.getName()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("用户名称重复"));
        //新增
        SysUser sysUser = BeanUtil.copyProperties(dto, SysUser.class);
        sysUser.setCreateTime(DateTime.now());
        //todo 后面补充
        sysUser.setCreateUser(1L);
        boolean save = this.save(sysUser);
        Assert.isTrue(save,()->new ServiceException("新增失败"));
        return sysUser.getId();
    }

    @Override
    public void updateUser(UserUpdateDTO dto) {
        //用户名称是唯一，排除本身
        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getName, dto.getName())
                .ne(SysUser::getId,dto.getId()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("用户名称重复"));
        //修改
        SysUser sysUser = BeanUtil.copyProperties(dto, SysUser.class);
        sysUser.setUpdateTime(DateTime.now());
        //todo 后面补充
        sysUser.setUpdateUser(1L);
        boolean update = this.updateById(sysUser);
        Assert.isTrue(update,()->new ServiceException("更新失败"));
    }

    @Override
    public void delete(Long id) {
        boolean remove = this.removeById(id);
        Assert.isTrue(remove,()->new ServiceException("删除失败"));
    }
}




