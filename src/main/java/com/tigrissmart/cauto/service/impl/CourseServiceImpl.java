package com.tigrissmart.cauto.service.impl;

import com.tigrissmart.cauto.dao.CourseRepository;
import com.tigrissmart.cauto.dao.entity.Course;
import com.tigrissmart.cauto.dto.CourseDto;
import com.tigrissmart.cauto.service.CourseService;
import com.tigrissmart.cauto.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TPage<CourseDto> getAllPageable(Pageable pageable) {
        Page<Course> data = courseRepository.findAll(pageable);
        TPage<CourseDto> response = new TPage<CourseDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), CourseDto[].class)));
        return response;
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        Course c = modelMapper.map(courseDto, Course.class);
        c = courseRepository.save(c);
        courseDto.setId(c.getId());
        return courseDto;
    }

    @Override
    public CourseDto getById(Long id) {
        Course c = courseRepository.getById(id);
        return modelMapper.map(c, CourseDto.class);
    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> data = courseRepository.findAll();
        return Arrays.asList(modelMapper.map(data, CourseDto[].class));
    }

    @Override
    public Boolean delete(Long id) {
        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public CourseDto update(Long id, CourseDto courseDto) {
        Course courseDb= courseRepository.getById(id);

        if (courseDb==null)
        {
            throw  new IllegalArgumentException("Teacher is not exist Id:"+id);
        }

        courseDb.setName(courseDto.getName());
        courseDb.setDescription(courseDto.getDescription());
        courseRepository.save(courseDb);
        return  modelMapper.map(courseDb,CourseDto.class);
    }
}
