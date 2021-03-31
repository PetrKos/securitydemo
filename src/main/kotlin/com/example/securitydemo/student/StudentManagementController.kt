package com.example.securitydemo.student

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("management/api/v1/students")
class StudentManagementController {

    val students = listOf(
        Student(1, "James Bond"),
        Student(2, "Maria Jones"),
        Student(3, "Anna Smith")
    )

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    fun getAllStudents(): List<Student> {
        print("getAllStudents")
        return students
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    fun registerNewStudent(@RequestBody student:Student) {

        print("registerNewStudent")
        print(student)
    }

    @DeleteMapping(path = ["{studentId}"])
    @PreAuthorize("hasAuthority('student:write')")
    fun deleteStudent(@PathVariable("studentId") studentId: Int) {
        print("deleteStudent")
        print(studentId)
    }

    @PutMapping(path =  ["{studentId}"])
    @PreAuthorize("hasAuthority('student:write')")
    fun updateStudent(@PathVariable("studentId") studentId: Int,
                      @RequestBody student: Student ) {

        print("updateStudent")
        print(String.format("%s %s", studentId, student))
    }

}