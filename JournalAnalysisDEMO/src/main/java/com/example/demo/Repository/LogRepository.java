package com.example.demo.Repository;

import com.example.demo.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
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

    @Query(value="select * from log where cname = :cname and uname like CONCAT('%',:uname,'%') and logauth <=:logauth and logname like CONCAT('%',:logname,'%') and uptime between :start and :end",nativeQuery=true)
    List<Log> findByCnameAndUnameAndUptimeAndLogauthAndLogname(@Param("cname")String cname,@Param("uname")String uname,@Param("logauth")String logauth,@Param("logname")String logname,@Param("start")String start,@Param("end")String end);

    @Query(value="select * from log where cname = :cname and uname like CONCAT('%',:uname,'%') and logauth <=:logauth and logname like CONCAT('%',:logname,'%')",nativeQuery=true)
    List<Log> findByCnameAndUnameAndLogauthAndLogname(@Param("cname")String cname,@Param("uname")String uname,@Param("logauth")String logauth,@Param("logname")String logname);

    @Query(value="select * from log where uname = :uname and logstate = 'finish' and logname like CONCAT('%',:logname,'%')",nativeQuery=true)
    List<Log> findByUnameAndLognameAndLogstate(@Param("uname") String uname,@Param("logname") String logname);

    @Query(value="select * from log where cname = :cname and uname like CONCAT('%',:uname,'%') and logauth <=:logauth and logname like CONCAT('%',:logname,'%') and logstate = 'finish'",nativeQuery=true)
    List<Log> findByCnameAndUnameAndLogauthAndLognameAndLogstate(@Param("cname")String cname,@Param("uname")String uname,@Param("logauth")String logauth,@Param("logname")String logname);

    @Query(value="select * from log where cname = :cname and uname like CONCAT('%',:uname,'%') and logauth <=:logauth and logstate = 'finish' and logname like CONCAT('%',:logname,'%') and uptime between :start and :end",nativeQuery=true)
    List<Log> findByCnameAndUnameAndUptimeAndLogauthAndLognameAndLogstate(@Param("cname")String cname,@Param("uname")String uname,@Param("logauth")String logauth,@Param("logname")String logname,@Param("start")String start,@Param("end")String end);
//    List<Log> findByUnameAndLogname(String uname,String logname);
}
