package com.example.securitydemo.security

import com.example.securitydemo.security.ApplicationUserPermission.*
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class ApplicationUserRole(private val permissions: Set<ApplicationUserPermission>) {
    STUDENT(setOf()),

    ADMIN(setOf(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),

    ADMINTRAINEE(setOf(COURSE_READ, STUDENT_READ));

    fun getGrantedAuthorities():Set<SimpleGrantedAuthority> {
        val permissions:MutableSet<SimpleGrantedAuthority> = permissions.map {
            SimpleGrantedAuthority(it.permission)
        }.toMutableSet()
        permissions.add(SimpleGrantedAuthority("ROLE_$name"))
        return permissions
    }

}