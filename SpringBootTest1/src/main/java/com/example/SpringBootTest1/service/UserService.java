package com.example.SpringBootTest1.service;

import com.example.SpringBootTest1.entity.User;

public interface UserService extends BaseService<User>{
	User findByName(String name);
}
