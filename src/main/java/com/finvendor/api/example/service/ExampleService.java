package com.finvendor.api.example.service;

import com.finvendor.api.example.dao.ExampleDao;
import com.finvendor.api.example.dto.ExampleDto;
import com.finvendor.api.example.dto.ExampleRequestDto;
import com.finvendor.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExampleService {

    @Autowired
    private ExampleDao exampleDao;

    public void saveOrUpdateExample(ExampleRequestDto exampleRequestDto) throws Exception {
        try {
            final Example exampleEntity = new Example();
            exampleEntity.setId(exampleRequestDto.getId());
            exampleEntity.setName(exampleRequestDto.getName());
            exampleEntity.setPhone(exampleRequestDto.getPhone());
            exampleDao.saveOrUpdate(exampleEntity);
            exampleDao.flush();
        } catch (RuntimeException e) {
            throw new Exception("Error has occurred while saving Example", e);
        }
    }

    public List<ExampleRequestDto> findAllExample() {
        List<Example> allEntity = exampleDao.findAll();
        List<ExampleRequestDto> exampleRequestDtoList = null;
        if (allEntity != null) {
            exampleRequestDtoList = new ArrayList<>();
            for (Example e : allEntity) {
                ExampleRequestDto exampleRequestDto = new ExampleRequestDto();
                exampleRequestDto.setId(e.getId());
                exampleRequestDto.setName(e.getName());
                exampleRequestDto.setPhone(e.getPhone());
                exampleRequestDtoList.add(exampleRequestDto);
            }
        }
        return exampleRequestDtoList;
    }

    public ExampleDto findExampleById(Serializable id) {
        Example existingExample = exampleDao.findById(id);
        ExampleDto exampleDto = null;
        if (existingExample != null) {
            exampleDto = new ExampleDto(existingExample.getId(), existingExample.getName(), existingExample.getPhone());
        }
        return exampleDto;
    }

    public void updateExample(ExampleRequestDto pojo) {
        Example exampleEntity = new Example();
        exampleEntity.setId(pojo.getId());
        exampleEntity.setName(pojo.getName());
        exampleEntity.setPhone(pojo.getPhone());
        exampleDao.saveOrUpdate(exampleEntity);
    }
}