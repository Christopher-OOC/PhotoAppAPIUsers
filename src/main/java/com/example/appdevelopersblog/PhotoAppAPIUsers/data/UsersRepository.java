package com.example.appdevelopersblog.PhotoAppAPIUsers.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
}