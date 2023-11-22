package com.example.webblog.repositories;

import com.example.webblog.models.Rate;
import com.example.webblog.models.RateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> getAllByPostPostId(Long postId);

    Boolean deleteAllByPost_PostId(Long postId);
    List<Rate> deleteAllByAccount_AccountId(Long postId);
    @Query("select r from Rate r where r.account.accountId = :accountId " +
            "and r.post.postId = :postId")
    Rate findByPostIdAndAccId(Long postId, Long accountId);
}
