package com.tigrissmart.cauto.dao;

import com.tigrissmart.cauto.dao.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository  extends JpaRepository<Course,Long> {
}
