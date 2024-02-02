package com.example.springboot.rdsdemo;

import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
}
