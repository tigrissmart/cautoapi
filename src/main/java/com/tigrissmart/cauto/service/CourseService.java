package com.tigrissmart.cauto.service;


import com.tigrissmart.cauto.dto.CourseDto;
import com.tigrissmart.cauto.dto.TeacherDto;
import com.tigrissmart.cauto.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    TPage<CourseDto> getAllPageable(Pageable pageable);
    CourseDto save(CourseDto courseDto);

    CourseDto getById(Long id);

    List<CourseDto> getCourses();

    Boolean delete(Long id);

    CourseDto update(Long id, CourseDto courseDto);
}
