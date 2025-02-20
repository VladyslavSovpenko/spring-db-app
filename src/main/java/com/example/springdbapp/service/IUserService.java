package com.example.springdbapp.service;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<Map<String, Object>> findAll();
}
