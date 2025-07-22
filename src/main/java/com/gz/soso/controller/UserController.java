package com.gz.soso.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.dto.*;
import com.gz.soso.pojo.vo.RoleListVO;
import com.gz.soso.pojo.vo.UserListVO;
import com.gz.soso.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SysUserService sysUserService;


    @GetMapping("/list-page")
    public WebResponse<IPage<UserListVO>> listPage (@RequestBody UserListDTO dto) {
        IPage<RoleListVO> deptListPage =sysUserService.listPage(dto);
        return WebResponseBuilder.success(deptListPage);
    }


    @PostMapping("/add")
    public WebResponse<Long> add (@Validated @RequestBody UserAddDTO dto) {
        Long id =sysUserService.add(dto);
        return WebResponseBuilder.success(id);
    }
    @PutMapping("/update")
    public WebResponse update (@Validated @RequestBody UserUpdateDTO dto) {
        sysUserService.updateUser(dto);
        return WebResponseBuilder.success();
    }

    @DeleteMapping("/delete")
    public WebResponse delete (@RequestParam Long id) {
        sysUserService.delete(id);
        return WebResponseBuilder.success();
    }


}
