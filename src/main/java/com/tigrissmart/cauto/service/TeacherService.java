package com.tigrissmart.cauto.service;

import com.tigrissmart.cauto.dto.TeacherDto;
import com.tigrissmart.cauto.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    TPage<TeacherDto> getAllPageable(Pageable pageable);

    TeacherDto save(TeacherDto teacherDto);

    TeacherDto getById(Long id);

    List<TeacherDto> getTeachers();

    Boolean delete(Long id);

    TeacherDto update(Long id, TeacherDto teacherDto);
}
