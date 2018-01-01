/**
 * 
 */
package com.finvendor.controller;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.finvendor.util.RequestConstans;

/**
 * @author rayulu vemula
 *
 */
@Controller
public class DocumentController {

	private static Logger logger = Logger.getLogger(DocumentController.class);
	
	
	/**
	 * method to download local documents
	 * 
	 * @return modelAndView
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value =RequestConstans.Document.DOCUMENT_DOWNLOAD, method = RequestMethod.GET)
	public ModelAndView downloadLocalDocuments(@RequestParam(value = "VeMu", required = false) String documentURL,
												@RequestParam(value = "RaYuL", required = false) String documentName) {
		ModelAndView modelAndView = new ModelAndView("downloadstaticdocuments");
		try {
			logger.info("downloadDocuments : documentName "+documentName+ " documentURL : "+documentURL);		
			documentURL = documentURL + "\\" + documentName;
			String viewName = FilenameUtils.getName(documentURL);
			System.out.println("document name---:" + documentName );
			System.out.println("doc url name---:" + documentURL );
			System.out.println("view name---:" + viewName );
			
			modelAndView.addObject("documentName", documentName);
			modelAndView.addObject("documentURL", documentURL);	
			modelAndView.addObject("viewName", viewName);
			
		} catch (Exception ex) {
			logger.error("Exception in load document file -- ", ex);
			modelAndView.addObject("errorMsg", "Unable to load Security type details, Please contact administrator");
		}
		return modelAndView;
	}
	

}
