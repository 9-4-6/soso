package com.gz.soso.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.dto.*;
import com.gz.soso.pojo.vo.RoleListVO;
import com.gz.soso.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final SysRoleService sysRoleService;


    @GetMapping("/list-page")
    public WebResponse<IPage<RoleListVO>> listPage (@RequestBody RoleListDTO dto) {
        IPage<RoleListVO> deptListPage =sysRoleService.listPage(dto);
        return WebResponseBuilder.success(deptListPage);
    }


    @PostMapping("/add")
    public WebResponse<Long> add (@Validated @RequestBody RoleAddDTO dto) {
        Long id =sysRoleService.add(dto);
        return WebResponseBuilder.success(id);
    }
    @PutMapping("/update")
    public WebResponse update (@Validated @RequestBody RoleUpdateDTO dto) {
        sysRoleService.updateRole(dto);
        return WebResponseBuilder.success();
    }

    @DeleteMapping("/delete")
    public WebResponse delete (@RequestParam Long id) {
        sysRoleService.delete(id);
        return WebResponseBuilder.success();
    }


}
