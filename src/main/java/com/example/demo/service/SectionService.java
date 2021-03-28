package com.example.demo.service;
import com.example.demo.entity.Section;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SectionDto;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
final SectionRepository sectionRepository;
final CourseRepository courseRepository;

    public SectionService(SectionRepository sectionRepository, CourseRepository courseRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
    }
    //  CREATE
    public ApiResponse addSection(SectionDto sectionDto){
        Section section=new Section();
        section.setName(sectionDto.getName());
        section.setDescription(section.getDescription());
        section.setCourse(courseRepository.findById(sectionDto.getCourseId()).get());
        sectionRepository.save(section);
        return new ApiResponse("object saved",true);
    }
    //READ
    public ApiResponse get(){

        return new ApiResponse("object ",true,sectionRepository.findAll());
    }
    //UPDATE
    public ApiResponse edit(Integer id,SectionDto sectionDto){
      Section section=sectionRepository.findById(id).get();
      section.setName(sectionDto.getName());
      section.setDescription(section.getDescription());
      section.setCourse(courseRepository.findById(sectionDto.getCourseId()).get());
      sectionRepository.save(section);
        return new ApiResponse("object edited",true);
    }
    //DELETE
    public ApiResponse delete( Integer id ){
        sectionRepository.deleteById(id);
        return new ApiResponse("object deleted",true);
    }
    //READ BY ID
    public ApiResponse getById( Integer id ){
        return new ApiResponse("object deleted",true,sectionRepository.findById(id));
    }

}
