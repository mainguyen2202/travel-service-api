package com.nlu.mainguyen.travelserviceapi.jwt.model;

import com.nlu.mainguyen.travelserviceapi.entities.Users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "token")
public class Token {
    @Column(name = "id")
    private @Id @GeneratedValue Long id;

    @Column(columnDefinition = "LONGTEXT", name = "access_token")
    private String accessToken;

    @Column(columnDefinition = "LONGTEXT", name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
