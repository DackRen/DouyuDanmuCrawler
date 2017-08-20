package com.ylu.dao;

import com.ylu.beans.Danmu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface DanmuRepository extends JpaRepository<Danmu, Long> {
    public Iterable<Danmu> findAllByCid(@Param(value = "cid")String cId);
    public Iterable<Danmu> findALLByUserId(@Param(value = "userId")String uId);
    public Iterable<Danmu> findAllByBnn(@Param(value = "bnn")String bnn);
    public Iterable<Danmu> findAllByUserLevel(@Param(value = "userLevel")String level);

//    @Query(value ="SELECT COUNT(d.bnn) FROM Danmu d GROUP BY bnn order by desc")
//    public List<Map<String, Object>> selectTopByBnn(int limit);
//    @Query(value ="SELECT COUNT(d.nn) FROM Danmu d GROUP BY bnn order by desc")
//    public List<Map<String, Object>> selectTopByNn(int limit);
//    @Query(value ="select count(DATE_FORMAT(FLOOR(mtime/#{interval})*#{interval},'%H:%i:%S')) as time from Danmu d where mtime between #{start} and #{end} group by time")
//    public List<Map<String, Object>> selectByTimeInterval(Timestamp start,Timestamp end,long interval);
}