package com.example.demo.Repository;

import com.example.demo.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log,Integer> {

    List<Log> findByUname(String uname);

    @Query(value="select * from log where logname like CONCAT('%',:logname,'%')",nativeQuery=true)
    List<Log> findByLogname(@Param("logname") String logname);

    @Query(value="select * from log where uname = :uname and logname like CONCAT('%',:logname,'%')",nativeQuery=true)
    List<Log> findByUnameAndLogname(@Param("uname")String uname,@Param("logname") String logname);

    @Query(value="select * from log where uptime between :time1 and :time2",nativeQuery=true)
    List<Log> findByUptime(@Param("time1")String time1,@Param("time2") String time2);

    @Query(value="select * from log where uname = :uname and uptime between :time1 and :time2",nativeQuery=true)
    List<Log> findByUnameAndUptime(@Param("uname")String uname,@Param("time1")String time1,@Param("time2") String time2);

    List<Log> findByCname(String cname);

//    List<Log> findByUnameAndLogname(String uname,String logname);
}
