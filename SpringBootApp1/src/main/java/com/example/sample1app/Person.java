package com.example.sample1app;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@NamedQuery(
        name="findWithName",
        query = "from Person where name like :fname"
)
@NamedQuery(
        name = "findByAge",
        query = "from Person where :min <= age and age <= :max")

@Entity
@Table(name="people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;
    
    @Column(length = 50, nullable = false)
    @NotBlank
    private String name;

    @Column(length = 200, nullable = true)
    @Email
    private String mail;

    @Column(nullable = true)
    @Min(0)
    @Max(200)
    private Integer age;

    @Column
    @Phone(onlyNumber = true)
    private String phone;
    
    @Column(nullable = true)
    private String memo;

    @OneToMany(mappedBy = "Person")
    @Column(nullable = true)
    private List<Message> messages;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {this.memo = memo;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
