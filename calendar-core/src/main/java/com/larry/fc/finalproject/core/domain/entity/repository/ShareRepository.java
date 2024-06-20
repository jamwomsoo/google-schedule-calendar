package com.larry.fc.finalproject.core.domain.entity.repository;

import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ShareRepository extends JpaRepository<Share, Long> {

    @Query("SELECT s from Share s where (s.fromUserId = ?1 or s.toUserId = ?1 and s.requestStatus = ?2 and s.direction = ?3)")
    List<Share> findAllByDirection(Long id, RequestStatus accepted, Share.Direction biDirection);


    List<Share> findAllByToUserIdAndRequestAndDirection(Long userId, RequestStatus requestStatus, Share.Direction direction);
}
