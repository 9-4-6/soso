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
import com.gz.soso.pojo.dto.DeptAddDTO;
import com.gz.soso.pojo.dto.DeptListDTO;
import com.gz.soso.pojo.dto.DeptUpdateDTO;
import com.gz.soso.pojo.entity.SysDept;
import com.gz.soso.pojo.vo.DeptListVO;
import com.gz.soso.service.SysDeptService;
import com.gz.soso.mapper.SysDeptMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【sys_dept(系统部门表)】的数据库操作Service实现
* @createDate 2025-06-28 22:47:42
*/
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept>
    implements SysDeptService{

    @Override
    public Long add(DeptAddDTO dto) {
        //部门名称是唯一
        long count = this.count(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptName, dto.getDeptName()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("部门名称重复"));
        //新增
        SysDept sysDept = BeanUtil.copyProperties(dto, SysDept.class);
        sysDept.setCreateTime(DateTime.now());
        //todo 后面补充
        sysDept.setCreateUser(1L);
        boolean save = this.save(sysDept);
        Assert.isTrue(save,()->new ServiceException("新增失败"));
        return sysDept.getId();
    }

    @Override
    public void updateDept(DeptUpdateDTO dto) {
        //部门名称是唯一，排除本身
        long count = this.count(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getDeptName, dto.getDeptName())
                .ne(SysDept::getId,dto.getId()));
        Assert.isTrue(count == CommonConstant.ZERO,()->new ServiceException("部门名称重复"));
        //修改
        SysDept sysDept = BeanUtil.copyProperties(dto, SysDept.class);
        sysDept.setUpdateTime(DateTime.now());
        //todo 后面补充
        sysDept.setUpdateUser(1L);
        boolean update = this.updateById(sysDept);
        Assert.isTrue(update,()->new ServiceException("更新失败"));

    }

    @Override
    public IPage<DeptListVO> listPage(DeptListDTO dto) {
        Page<SysDept> page = new Page<>(dto.getCurrent(), dto.getSize());

        // 构建查询条件
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getDeptName()), SysDept::getDeptName, dto.getDeptName());

        // 执行分页查询
        Page<SysDept> deptPage = this.page(page, queryWrapper);
        return deptPage.convert(dept -> {
            DeptListVO vo = new DeptListVO();
            BeanUtils.copyProperties(dept, vo);
            return vo;
        });
    }

    @Override
    public void delete(Long id) {
        boolean remove = this.removeById(id);
        Assert.isTrue(remove,()->new ServiceException("删除失败"));

    }
}




