<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="personaldetails" value="<%=RequestConstans.Vendor.PERSONALDETAILS %>"> </c:set>
<c:set var="supportcoverage" value="<%=RequestConstans.Vendor.SUPPORTCOVERAGE %>"> </c:set>
<c:set var="supportdetails" value="<%=RequestConstans.Vendor.SUPPORTDETAILS %>"> </c:set>
<c:set var="awarddetails" value="<%=RequestConstans.Vendor.AWARDDETAILS %>"> </c:set>
<c:set var="datadistribution" value="<%=RequestConstans.Vendor.DATADISTRIBUTION %>"> </c:set>
<c:set var="databuyers" value="<%=RequestConstans.Vendor.SEARCHDATABUYERS %>"> </c:set>
<c:set var="myrfp" value="<%=RequestConstans.Vendor.MYRFP %>"> </c:set>

<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>finvendor</title>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<div class="portlet-body form">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
										<%-- <div class="ColumnCommonmyprofile" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Personal Details</a> </div></div>
										<div class="ColumnCommonmyprofile" id="change1"> <div class="lable_header" id="interdiv1">  <a id="ancho1" href="#tab2" class="lable_header" data-toggle="tab" onclick="activeMode('${supportcoverage}');">Support Coverage</a> </div> </div>
										<!-- <div class="ColumnCommonmyprofile" > <div class="lable_header"> <a href="#tab3" class="lable_header" data-toggle="tab">Support Details</a> </div> </div> -->
										<div class="ColumnCommonmyprofile" id="change2"> <div class="lable_header" id="interdiv2">  <a id="ancho2" href="#tab3" class="lable_header" data-toggle="tab" onclick="activeMode('${awarddetails}');">Award Details</a> </div> </div>
										 <!-- <div class="ColumnCommonmyprofile"> <div class="lable_header"> <a href="#tab4" class="lable_header" data-toggle="tab">Data Distribution</a> </div> </div> -->
										 <div class="ColumnCommonmyprofile" id="change3"> <div class="lable_header" id="interdiv3"> <a id="ancho3" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeMode('${databuyers}');">Search Data-Buyers</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="change4"> <div class="lable_header" id="interdiv4"> <a id="ancho4" href="#tab5" class="lable_header" data-toggle="tab" onclick="activeMode('${myrfp}');">My RFP</a> </div> </div> --%>
										<div class="ColumnCommonrfpinbox"> <div class="lable_header" > <a href="#tab8" class="lable_header" data-toggle="tab">RFP Inbox</a> </div> </div>
										</div>
								<div class="tab-content">
									<div class="tab-pane active" id="tab8">
									<div><br/></div>
									<div class="Rowtableinfoval" >
									   <div class="ColumnCommonmyprofinputsearch"> 
											<select name="rfpcompany" class="m-wrap largevalra inpubackground">
											     <option value ="-SELECT-"> -Select RFP By Company- </option>
											      <option value ="-SELECT-"> JP Morgan </option>
											      <option value ="-SELECT-"> AXA Fin Corp </option>
											      <option value ="-SELECT-"> MAX Bupa Fin Corp </option>
											</select>
										</div>
										<div class="ColumnCommonmyprofinputsearch"> 
											<select name="rfpassetclass" class="m-wrap largevalra inpubackground">
											     <option value ="-SELECT-"> -Select Asset Class- </option>
											     <c:forEach var="assetClasses" items="${assetClasses}">
											    	<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
											 	 </c:forEach>
											</select>
										</div> 
										<div class="ColumnCommonmyprofinputsearch"> 
										  <input type="text"  placeholder="Enter RFP Issue DT" class="m-wrap largeval inpubackground"  id="inputField" name="rfpissuedate"/>
										 </div> 
										<div class="ColumnCommonmyprofinputsearch"> 
											<input type="text"  placeholder="Enter RFP End DT" class="m-wrap largeval inpubackground"  id="inputField1" name="rfpenddate"/>
										</div>
										<div class="ColumnCommonmyprofinputsearch">
											<div class="se">
												<a  href="#" class="btn buttoncheck">Search</a>
													<input type="reset" value="Clear" class="btn buttoncheck"/>
												</div>
										</div>
									</div>
									<div><br/></div>
										  <div class="Rowtableaction" >  
												<div class="ColumnCommonmyprofileaction">  <div class="lable_headeractionbuttons"> <a href="#tab12" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step">Submit final Response</a> </div></div>
												<div class="ColumnCommonmyprofileaction">  <div class="lable_headeractionbuttons"> <a href="#tab11" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step"> Ask for More Info</a> </div></div>
												<div class="ColumnCommonmyprofileaction"> <div class="lable_headeractionbuttons"> <a href="#tab10" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step">Express an Interest</a> </div></div>
												<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab9" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step">See the Description</a> </div> </div>
										  </div> 
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectvendordashrfpinbox" id="sampledistribution">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFP By Company</th>
															<th>RFP Title</th>
															<th>Asset-Class</th>
															<th>Region</th>
															<th>RFP Issue DT</th>
															<th>RFP End DT</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>JP Morgan</td>
															<td>Equity Index Constituent Data</td>
															<td>Equity</td>
															<td>Asia</td>
															<td>09/09/2019</td>
															<td>11/11/2021</td>
														</tr>
														<tr id="2">
															<td>AXA Fin Corp</td>
															<td>Fixed Income Constituent Data </td>
															<td>Fixed Income</td>
															 <td>Africa</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
														</tr>
														<tr id="3">
															<td>American Fina pvt.ltd</td>
															<td>Indices Constituent Data</td>
															<td>Indices</td>
															 <td>North America</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
														</tr>
														<tr id="3">
															<td>MAX Bupa Fin corp ltd</td>
															<td>Derivates Constituent Data</td>
															<td>Derivates</td>
															 <td>South America</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab4" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
											</div>  
											
									<div class="tab-pane" id="tab9">
									 		 <div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    <a onclick="openWindow();">It is easier to find a Web-based vendor that sells the item I wish to purchase.</a> 
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
										</div>  
										<div class="tab-pane" id="tab10">
											 <div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    It is easier to find a Web-based vendor that sells the item I wish to purchase.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
											</div> 
											<div class="tab-pane" id="tab11">
													<div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												   It is easier to find a Web-based vendor that sells the item I wish to purchase.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
											</div>  
											
											<div class="tab-pane" id="tab12">
													<div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    It is easier to find a Web-based vendor that sells the item I wish to purchase.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
											</div>  
											
											<div class="tab-pane" id="tab13">
													<div><br/></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   1) It is easier to find a Web-based vendor that sells the item I wish to purchase?
												    <br>
												    <input type="text" class="m-wrap large">
												</p>
												<p> 
												   2) I can quickly gather information about products and services I wish to purchase from Web-based vendors?
												    <br>
												    <input type="text" class="m-wrap large">
												</p>
												<p> 
												   3) Web-based vendors deliver orders/services in a more timely manner?
												   <br>
												    <input type="text" class="m-wrap large">
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step">Back</a> 
														<input type="reset" value="submit response" class="btn buttoncheck"/>
													</div>
												</div>  
											</div>  
											
											</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				</div>
				</div>
    </div>
    </div>
    <!-- END CONTAINER -->
    <jsp:include page="common/footer.jsp"></jsp:include>
	<!-- Date plugins start-->
		<script>
		  $(function() {
		    $( "#inputField" ).datepicker();
		    $( "#inputField1" ).datepicker();
		  });
	  </script>
	  <script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
  
  <!-- Date plugins start-->
</body>
</html>
