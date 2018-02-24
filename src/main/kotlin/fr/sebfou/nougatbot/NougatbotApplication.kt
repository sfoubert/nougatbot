package fr.sebfou.nougatbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class NougatbotApplication

fun main(args: Array<String>) {
    SpringApplication.run(NougatbotApplication::class.java, *args)
}
