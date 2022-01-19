package com.tigrissmart.cauto.service.impl;


import com.tigrissmart.cauto.dao.TeacherRepository;
import com.tigrissmart.cauto.dao.entity.Student;
import com.tigrissmart.cauto.dao.entity.Teacher;


import com.tigrissmart.cauto.dto.StudentDto;
import com.tigrissmart.cauto.dto.TeacherDto;
import com.tigrissmart.cauto.service.TeacherService;
import com.tigrissmart.cauto.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TPage<TeacherDto> getAllPageable(Pageable pageable) {
        Page<Teacher> data = teacherRepository.findAll(pageable);
        TPage<TeacherDto> response = new TPage<TeacherDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), TeacherDto[].class)));
        return response;
    }

    @Override
    public TeacherDto save(TeacherDto teacherDto) {
        Teacher t = modelMapper.map(teacherDto, Teacher.class);
        t = teacherRepository.save(t);
        teacherDto.setId(t.getId());
        return teacherDto;
    }

    @Override
    public TeacherDto getById(Long id) {
        Teacher t = teacherRepository.getById(id);
        return modelMapper.map(t, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> getTeachers() {
        List<Teacher> data = teacherRepository.findAll();
        return Arrays.asList(modelMapper.map(data, TeacherDto[].class));
    }

    @Override
    public Boolean delete(Long id) {
        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public TeacherDto update(Long id, TeacherDto teacherDto) {
        Teacher teacherDb= teacherRepository.getById(id);

        if (teacherDb==null)
        {
            throw  new IllegalArgumentException("Teacher is not exist Id:"+id);
        }

        teacherDb.setFirstname(teacherDto.getFirstname());
        teacherDb.setLastname(teacherDto.getLastname());
        teacherRepository.save(teacherDb);
        return  modelMapper.map(teacherDb,TeacherDto.class);
    }
}
