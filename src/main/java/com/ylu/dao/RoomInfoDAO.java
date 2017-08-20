package com.ylu.dao;

import com.ylu.beans.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomInfoDAO extends JpaRepository<RoomInfo, Long>{
}