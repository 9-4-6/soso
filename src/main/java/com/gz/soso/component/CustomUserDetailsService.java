package com.gz.soso.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gz.soso.mapper.SysUserMapper;
import com.gz.soso.pojo.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
    static final class CustomUserDetails  implements UserDetails {
        private Long id;
        private String username;
        private String password;
        private String name;
        private String avatar;
        private Integer status;

        public CustomUserDetails(SysUser sysUser) {
            this.id = sysUser.getId();
            this.username = sysUser.getAccount();
            this.avatar = sysUser.getAvatar();
            this.name =sysUser.getName();
            this.password = sysUser.getPassword();
            this.status = sysUser.getStatus();
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAvatar() {
            return avatar;
        }

        public Integer getStatus() {
            return status;
        }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getUsername() {
            return username;
        }
        @Override
        public String getPassword() {
            return password;
        }
    }
}
