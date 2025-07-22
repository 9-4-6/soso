package com.gz.soso.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gz.soso.pojo.dto.DeptAddDTO;
import com.gz.soso.pojo.dto.DeptListDTO;
import com.gz.soso.pojo.dto.DeptUpdateDTO;
import com.gz.soso.pojo.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gz.soso.pojo.vo.DeptListVO;

/**
* @author HP
* @description 针对表【sys_dept(系统部门表)】的数据库操作Service
* @createDate 2025-06-28 22:47:42
*/
public interface SysDeptService extends IService<SysDept> {
    /**
     * 部门新增
     * @param dto
     * @return
     */
    Long add(DeptAddDTO dto);

    /**
     * 修改部门
     * @param dto
     */
    void updateDept(DeptUpdateDTO dto);

    /**
     * 部门分页列表查询
     * @param dto
     * @return
     */
    IPage<DeptListVO> listPage(DeptListDTO dto);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
