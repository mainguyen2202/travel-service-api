package com.nlu.mainguyen.travelserviceapi.entities;

// import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nlu.mainguyen.travelserviceapi.jwt.model.Role;
import com.nlu.mainguyen.travelserviceapi.jwt.model.Token;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users implements UserDetails {
    private @Id @GeneratedValue Long id;

    private String name;
    private String email;
    private String username;
    private String password;
    private String image;
    private int status;
    private int role;
    private Date createAt;
    private String resetPasswordToken;
    private Date resetPasswordTokenExpirationDate;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Articles> articles = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Itineraries> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Feedbacks> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ShareItineraries> shareItineraries = new ArrayList<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (this.role) {
            case 1:
                authorities.add(new SimpleGrantedAuthority("USER"));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                break;
            // Thêm các case khác tương ứng với các role khác
            default:
                // Xử lý trường hợp role không hợp lệ
              
                break;
        }
        return authorities;
    }

}
