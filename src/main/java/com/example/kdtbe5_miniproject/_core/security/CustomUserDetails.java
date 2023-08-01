package com.example.kdtbe5_miniproject._core.security;

import com.example.kdtbe5_miniproject.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    //TODO : UserRoles를 Enum으로 변경하면서 오류가 생겨 getAuthorities(), isAdmin() 메서드 내용 수정했는데 확인 필요할 것 같아요
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(()-> "ROLE_"+user.getRoles().getTypeNumber());
        return authorities;
//        return Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public Long getUserId() {
        return user.getId();
    }

    public boolean isAdmin() {
        if(user.getRoles().getTypeNumber()==1)
            return true;
        return false;
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
}
