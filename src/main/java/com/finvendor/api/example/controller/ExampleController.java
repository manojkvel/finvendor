package com.finvendor.api.example.controller;

import com.finvendor.api.example.dto.ExampleRequestDto;
import com.finvendor.api.example.service.ExampleService;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author ayush on April 30, 2018
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class ExampleController {

    private ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello Friends...", HttpStatus.OK);
    }

    @PostMapping(value = "/examples", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveExample(@Valid @RequestBody ExampleRequestDto exampleRequestDto) throws WebApiException {
        final Example example1Entity = new Example();
        example1Entity.setId(exampleRequestDto.getId());
        example1Entity.setName(exampleRequestDto.getName());
        example1Entity.setPhone(exampleRequestDto.getPhone());
        exampleService.saveOrUpdateExample1(example1Entity);
    }

    @GetMapping(value = "/examples", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExampleRequestDto>> findAllExample() throws WebApiException {
        List<ExampleRequestDto> allExample = exampleService.findAllExample();
        return new ResponseEntity<>(allExample, HttpStatus.OK);
    }

    @GetMapping(value = "/examples/{exampleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExampleRequestDto findExample(
            @PathVariable @Size(min = 1, max = 3, message = "example id must be [1-3] digit max ") String exampleId)
            throws WebApiException {
        ExampleRequestDto pojo = new ExampleRequestDto();
        pojo.setId(1);
        pojo.setName("Dummy");
        return pojo;
    }

    @RequestMapping(value = "/examples/{exampleId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateExample(@PathVariable String exampleId, @RequestBody ExampleRequestDto exampleRequestDto) throws WebApiException {
        exampleService.updateExample(exampleRequestDto);
    }
}
