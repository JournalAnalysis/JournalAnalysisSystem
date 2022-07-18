package com.example.demo.Repository;

import com.example.demo.entity.Log;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByUname(String uname);

    List<User> findByCname(String cname);

    @Query(value="select * from user where cname = :cname and utype = :utype and uname like CONCAT('%',:uname,'%')",nativeQuery=true)
    List<User> findByCnameAndUtypeAndUname(@Param("cname")String cname, @Param("utype")String utype, @Param("uname")String uname);
}
