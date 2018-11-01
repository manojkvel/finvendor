//package com.finvendor.serverwebapi.resources.example;
//
//import com.finvendor.server.example.staticpojo.ExampleDto;
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.WebUriConstants;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
///**
// * This interface is for quick testing at web level
// *
// * @author ayush on April 30, 2018
// */
//@RequestMapping(value = WebUriConstants.BASE_URI)
//public interface IWebExample {
//
//    @RequestMapping(value = "/saveexample", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    void saveExample(ExampleDto examplePojo) throws WebApiException;
//
//    @RequestMapping(value = "/findallexample", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    List<ExampleDto> findAllExample() throws WebApiException;
//
//    @RequestMapping(value = "/findexample", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    ExampleDto findExample() throws WebApiException;
//
//    @RequestMapping(value = "/updateexample", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    void updateExample(ExampleDto examplePojo) throws WebApiException;
//
//    @RequestMapping("/contact/{id}")
//    @ResponseBody
//    Contact contact(@PathVariable("id") Integer id);
//}
