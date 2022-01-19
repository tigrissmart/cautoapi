package com.tigrissmart.cauto.service.impl;

import com.tigrissmart.cauto.dao.StudentRepository;
import com.tigrissmart.cauto.dao.entity.Student;
import com.tigrissmart.cauto.dto.StudentDto;
import com.tigrissmart.cauto.service.StudentService;
import com.tigrissmart.cauto.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TPage<StudentDto> getAllPageable(Pageable pageable) {
        Page<Student> data = studentRepository.findAll(pageable);
        TPage<StudentDto> response = new TPage<StudentDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), StudentDto[].class)));
        return response;
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student s = modelMapper.map(studentDto, Student.class);
        s = studentRepository.save(s);
        studentDto.setId(s.getId());
        return studentDto;
    }

    @Override
    public StudentDto getById(Long id) {
        Student s = studentRepository.getById(id);
        return modelMapper.map(s, StudentDto.class);
    }

    @Override
    public List<StudentDto> getStudents() {
        List<Student> data = studentRepository.findAll();
        return Arrays.asList(modelMapper.map(data, StudentDto[].class));
    }

    @Override
    public Boolean delete(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Transactional
    public StudentDto update(Long id, StudentDto studentDto) {
        Student studentDb= studentRepository.getById(id);

        if (studentDb==null)
        {
            throw  new IllegalArgumentException("Student is not exist Id:"+id);
        }
        studentDb.setNo(studentDto.getNo());
        studentDb.setFirstname(studentDto.getFirstname());
        studentDb.setLastname(studentDto.getLastname());
        studentDb.setAddress(studentDto.getAddress());
        studentRepository.save(studentDb);
        return  modelMapper.map(studentDb,StudentDto.class);

    }
}
