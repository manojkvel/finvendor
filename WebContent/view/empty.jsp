<<<<<<< HEAD
<%
String status = "";
if(request.getAttribute("status") != null){
	status = request.getAttribute("status").toString().trim();
}
out.println(status);
=======
<%
String status = "";
if(request.getAttribute("status") != null){
	status = request.getAttribute("status").toString().trim();
}
out.println(status);
>>>>>>> origin/master
%>