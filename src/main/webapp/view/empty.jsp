<%
String status = "";
if(request.getAttribute("status") != null){
	status = request.getAttribute("status").toString().trim();
}
out.println(status);
%>