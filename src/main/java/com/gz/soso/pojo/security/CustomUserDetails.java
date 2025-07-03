package com.gz.soso.pojo.security;

import com.gz.soso.pojo.entity.SysUser;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {


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
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
