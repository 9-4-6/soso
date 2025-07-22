package com.gz.soso.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.dto.PostAddDTO;
import com.gz.soso.pojo.dto.PostListDTO;
import com.gz.soso.pojo.dto.PostUpdateDTO;
import com.gz.soso.pojo.entity.SysPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gz.soso.pojo.vo.PostListVO;

/**
* @author HP
* @description 针对表【sys_position(系统岗位表)】的数据库操作Service
* @createDate 2025-06-28 22:47:36
*/
public interface SysPositionService extends IService<SysPosition> {
    /**
     * 分页列表查询
     *
     * @param dto
     * @return
     */
    IPage<PostListVO> listPage(PostListDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long add(PostAddDTO dto);

    /**
     * 更新
     * @param dto
     */
    void updatePost(PostUpdateDTO dto);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
