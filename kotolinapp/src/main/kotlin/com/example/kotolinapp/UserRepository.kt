package com.example.kotolinapp

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Int>
// {
//
//    List<News> getNews(id: Int);
// }