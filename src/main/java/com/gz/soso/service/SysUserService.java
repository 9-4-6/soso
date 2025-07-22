package com.gz.soso.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.dto.UserAddDTO;
import com.gz.soso.pojo.dto.UserListDTO;
import com.gz.soso.pojo.dto.UserUpdateDTO;
import com.gz.soso.pojo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gz.soso.pojo.vo.RoleListVO;

/**
* @author HP
* @description 针对表【sys_user(系统用户表)】的数据库操作Service
* @createDate 2025-06-28 22:45:35
*/
public interface SysUserService extends IService<SysUser> {
    /**
     * 分页列表
     * @param dto
     * @return
     */
    IPage<RoleListVO> listPage(UserListDTO dto);

    /**
     * 新增
     * @param dto
     * @return
     */
    Long add(UserAddDTO dto);

    /**
     * 更新
     * @param dto
     */
    void updateUser(UserUpdateDTO dto);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
