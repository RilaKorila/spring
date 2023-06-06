package com.example.kotolinapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotolinAppApplication

fun main(args: Array<String>) {
	runApplication<KotolinAppApplication>(*args)
}
