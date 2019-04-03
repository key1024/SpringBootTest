package com.example.SpringBootTest1.mapper;

import com.example.SpringBootTest1.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    List<User> findById(Long id);

    void create(User user);

    void delete(Long id);

    void update(User user);

    User findByName(String name);
}
