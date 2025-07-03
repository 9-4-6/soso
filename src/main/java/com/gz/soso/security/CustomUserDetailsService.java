package com.gz.soso.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gz.soso.mapper.SysUserMapper;
import com.gz.soso.pojo.entity.SysUser;
import com.gz.soso.pojo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 自定义用户详情服务实现
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser =sysUserMapper
                .selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount,username));
        if (sysUser == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new CustomUserDetails(sysUser);
    }
}
