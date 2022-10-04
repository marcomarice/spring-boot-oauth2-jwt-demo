package it.bitrock.springbootoauth2jwtdemo.mapper;

import it.bitrock.springbootoauth2jwtdemo.dto.CurriculumDto;
import it.bitrock.springbootoauth2jwtdemo.model.Curriculum;

public class CurriculumMapper {

    private CurriculumMapper() {}

    public static CurriculumDto fromCurriculumToCurriculumDto(Curriculum curriculum) {
        CurriculumDto curriculumDto = new CurriculumDto();
        curriculumDto.setId(curriculum.getId());
        curriculumDto.setName(curriculum.getName());
        curriculumDto.setSurname(curriculum.getSurname());
        curriculumDto.setFile(curriculum.getFile());
        curriculumDto.setUploadDate(curriculum.getCreatedOn());
        curriculumDto.setStatus(curriculum.getStatus().getName());
        return curriculumDto;
    }
}
