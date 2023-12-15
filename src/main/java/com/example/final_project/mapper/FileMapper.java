package com.example.final_project.mapper;

import com.example.final_project.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insert(FileDto fileDto);
    void delete(Long boardNumber);
    List<FileDto> selectList(Long boardNumber);
}










