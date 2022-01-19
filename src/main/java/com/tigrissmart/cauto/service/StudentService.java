package com.tigrissmart.cauto.service;

import com.tigrissmart.cauto.dto.StudentDto;
import com.tigrissmart.cauto.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    TPage<StudentDto> getAllPageable(Pageable pageable);
    StudentDto save(StudentDto studentDto);

    StudentDto getById(Long id);

    List<StudentDto> getStudents();

    Boolean delete(Long id);

    StudentDto update(Long id, StudentDto studentDto);
}
