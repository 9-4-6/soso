package com.gz.soso.controller;

import com.gz.soso.security.UnifiedAuthService;
import com.gz.soso.pojo.dto.LoginDTO;
import com.gz.soso.pojo.vo.LoginVO;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final UnifiedAuthService unifiedAuthService;
    @PostMapping("/login")
    public WebResponse<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        LoginVO loginVO = unifiedAuthService.login(loginDTO);
        return WebResponseBuilder.success(loginVO);
    }
    @PostMapping("/index")
    public WebResponse<String> index() {
        return WebResponseBuilder.success("hello world");
    }
}
