<%@page import="com.finvendor.util.RequestConstans"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.finvendor.util.CommonUtils"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<head>
<script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	var referertext = "<%=request.getHeader("Referer")%>";
	var currenturl = "<%=request.getAttribute("javax.servlet.forward.request_uri")%>";
	if(referertext == "null" && currenturl != "<%=request.getContextPath()%>/<%=RequestConstans.Home.HOME_PAGE%>"){
		illegalAccess();
	}
});
function illegalAccess(event){
	window.location.href = "<%=request.getContextPath()%>";
	event.preventDefault();
}

</script>
</head>

<%
	int read = 0;

	String documentName = (String) request.getAttribute("documentName");		
	String documentUrl = (String) request.getAttribute("documentURL");
	String viewName = (String) request.getAttribute("viewName");

	String contentType = CommonUtils.getFileContentType(FilenameUtils.getExtension(documentName));	
	
	response.setContentType(contentType);
	response.setHeader("Content-Disposition", "attachment; filename=\""+viewName+"\"");
	response.setHeader("cache-control", "no-cache");
	
	try{
		File file = new File(documentUrl);
		FileInputStream fileInputStream = new FileInputStream(file);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
		byte[] buffer = new byte[fileInputStream.available()];
				
        while ((read = fileInputStream.read(buffer)) != -1 ) {
        	byteArrayOutputStream.write(buffer, 0, read);
        }

        out.clear();
        out = pageContext.pushBody();
        
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(byteArrayOutputStream.toByteArray());
        fileInputStream.close();
        
	}catch(Exception ex){
		 
		}
	
	
%>
