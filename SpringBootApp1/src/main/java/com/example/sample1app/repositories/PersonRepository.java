package main.java.com.example.sample1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.com.example.sample1app.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
