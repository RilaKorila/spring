package com.example.sample1app.repositories;

import com.example.sample1app.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    public Optional<Message> findById(Long id);
}
