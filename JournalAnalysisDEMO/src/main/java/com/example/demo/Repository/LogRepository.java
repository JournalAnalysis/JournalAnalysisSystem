package com.example.demo.Repository;

import com.example.demo.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log,Integer> {

    List<Log> findByUname(String uname);
    List<Log> findByLogid(String logid);

}
