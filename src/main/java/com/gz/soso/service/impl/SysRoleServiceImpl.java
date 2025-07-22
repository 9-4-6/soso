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
import com.gz.soso.pojo.dto.RoleAddDTO;
import com.gz.soso.pojo.dto.RoleListDTO;
import com.gz.soso.pojo.dto.RoleUpdateDTO;
import com.gz.soso.pojo.entity.SysRole;
import com.gz.soso.pojo.vo.RoleListVO;
import com.gz.soso.service.SysRoleService;
import com.gz.soso.mapper.SysRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【sys_role(系统角色表)】的数据库操作Service实现
* @createDate 2025-06-28 22:47:29
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Override
    public IPage<RoleListVO> listPage(RoleListDTO dto) {
        Page<SysRole> page = new Page<>(dto.getCurrent(), dto.getSize());
        // 构建查询条件
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getRoleName()),
                SysRole::getRoleName, dto.getRoleName());

        // 执行分页查询
        Page<SysRole> deptPage = this.page(page, queryWrapper);
        return deptPage.convert(role -> {
            RoleListVO vo = new RoleListVO();
            BeanUtils.copyProperties(role, vo);
            return vo;
        });
    }

    @Override
    public Long add(RoleAddDTO dto) {
        //角色名称是唯一
        long count = this.count(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRoleName, dto.getRoleName()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("角色名称重复"));
        //新增
        SysRole sysRole = BeanUtil.copyProperties(dto, SysRole.class);
        sysRole.setCreateTime(DateTime.now());
        //todo 后面补充
        sysRole.setCreateUser(1L);
        boolean save = this.save(sysRole);
        Assert.isTrue(save,()->new ServiceException("新增失败"));
        return sysRole.getId();
    }

    @Override
    public void updateRole(RoleUpdateDTO dto) {
        //角色名称是唯一，排除本身
        long count = this.count(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRoleName, dto.getRoleName())
                .ne(SysRole::getId,dto.getId()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("角色名称重复"));
        //修改
        SysRole sysRole = BeanUtil.copyProperties(dto, SysRole.class);
        sysRole.setUpdateTime(DateTime.now());
        //todo 后面补充
        sysRole.setUpdateUser(1L);
        boolean update = this.updateById(sysRole);
        Assert.isTrue(update,()->new ServiceException("更新失败"));
    }

    @Override
    public void delete(Long id) {
        boolean remove = this.removeById(id);
        Assert.isTrue(remove,()->new ServiceException("删除失败"));
    }
}




