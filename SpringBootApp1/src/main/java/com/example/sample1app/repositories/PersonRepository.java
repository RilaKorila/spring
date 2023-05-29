package com.example.sample1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import com.example.sample1app.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT d FROM Person d ORDER BY d.name")
    public List<Person> findAllOrderByName();

    public Optional<Person> findById(Long name);
}
