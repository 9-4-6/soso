package com.gz.soso.controller;

import com.gz.soso.component.UnifiedAuthService;
import com.gz.soso.pojo.dto.LoginDTO;
import com.gz.soso.pojo.vo.LoginVO;
import com.gz.soso.pojo.webRes.WebResponse;
import com.gz.soso.pojo.webRes.WebResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final UnifiedAuthService unifiedAuthService;

    @PostMapping("/login")
    public WebResponse<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        // 执行认证
        Authentication authentication = unifiedAuthService.executeAuthenticate(loginDTO);
        // todo 生成令牌
        //String token = tokenProvider.generateToken(authentication);
        // todo 存储redis key:userId + platformId + uuid  value:用户信息
        return WebResponseBuilder.success("登录成功");
    }
}
