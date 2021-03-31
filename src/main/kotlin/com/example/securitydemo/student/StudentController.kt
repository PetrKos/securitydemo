package com.example.securitydemo.student

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/students")
class StudentController {

    val students = listOf(
        Student(1, "James Bond"),
        Student(2, "Maria Jones"),
        Student(3, "Anna Smith")
    )

    @GetMapping("{studentId}")
    fun getStudent(@PathVariable studentId: Int): Student? {
        return students.filter { it.studentId == it.studentId }
            .find { it.studentId == studentId}

    }
}