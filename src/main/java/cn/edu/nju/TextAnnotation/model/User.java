package cn.edu.nju.TextAnnotation.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户表
 *
 * @author keenan on 2018/5/16
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer user_id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    /**
     * 0普通用户
     * 1经理
     */
    private Integer role;

    public User() {
    }

    public User(String name, String password, Integer role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.role == 0) {
            authorities.add(new SimpleGrantedAuthority("USER"));
        } else if (this.role == 1) {
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        }

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
