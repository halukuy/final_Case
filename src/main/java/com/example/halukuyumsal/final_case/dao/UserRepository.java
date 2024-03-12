package com.example.halukuyumsal.final_case.dao;

import com.example.halukuyumsal.final_case.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long > {
}
