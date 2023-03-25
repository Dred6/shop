package com.davydovandrey.shop.repository;

import com.davydovandrey.shop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByDeleted(boolean bool);

    Person findByUsername(String username);
}
