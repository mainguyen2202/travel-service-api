package com.nlu.mainguyen.travelserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.mainguyen.travelserviceapi.entities.PasswordResetToken;
import com.nlu.mainguyen.travelserviceapi.entities.Users;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    // PasswordResetToken findByUser(Users user);

    // Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    // void deleteByExpiryDateLessThan(Date now);

    // void deleteByUserId(long user_id);
    @Transactional
    void deleteByUserId(long userId);

    // @Modifying
    // @Query("delete from password_reset_tokens t where t.expiryDate <= ?1")
    // void deleteAllExpiredSince(Date now);
}