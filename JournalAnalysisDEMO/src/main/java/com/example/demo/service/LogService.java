package com.example.demo.service;

import com.example.demo.entity.Log;
import com.example.demo.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;
}
