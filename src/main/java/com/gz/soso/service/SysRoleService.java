package com.gz.soso.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.dto.RoleAddDTO;
import com.gz.soso.pojo.dto.RoleListDTO;
import com.gz.soso.pojo.dto.RoleUpdateDTO;
import com.gz.soso.pojo.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gz.soso.pojo.vo.RoleListVO;

/**
* @author HP
* @description 针对表【sys_role(系统角色表)】的数据库操作Service
* @createDate 2025-06-28 22:47:29
*/
public interface SysRoleService extends IService<SysRole> {
    /**
     * 列表分页
     * @param dto
     * @return
     */
    IPage<RoleListVO> listPage(RoleListDTO dto);

    /**
     * 新增
     * @param dto
     * @return
     */
    Long add(RoleAddDTO dto);

    /**
     * 更新
     * @param dto
     */
    void updateRole(RoleUpdateDTO dto);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
