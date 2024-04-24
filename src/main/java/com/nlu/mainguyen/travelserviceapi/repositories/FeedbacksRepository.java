package com.nlu.mainguyen.travelserviceapi.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.mainguyen.travelserviceapi.entities.Feedbacks;

public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {

    @Query(value = "SELECT * FROM feedbacks WHERE  articles_id = ?1", nativeQuery = true)
    List<Feedbacks> findAllByArticlesId( long articlesId );// B1

    @Query(value = "SELECT * FROM feedbacks WHERE users_id = ?1 AND articles_id = ?2", nativeQuery = true)
    Optional<Feedbacks> findByUsersIdAndArticlesId(long usersId, long articlesId);

    @Query(value = "   SELECT * FROM feedbacks WHERE heart = ?1 AND articles_id = ?2", nativeQuery = true)
    List<Feedbacks> findAllByHeart( int heart ,long articlesId );// B1


}