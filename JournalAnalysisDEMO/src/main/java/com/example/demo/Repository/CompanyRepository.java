package com.example.demo.Repository;

import com.example.demo.entity.Company;
import com.example.demo.entity.Log;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    List<Company> findByCname(String Cname);

    List<Company> findByCcode(String Ccode);

}
