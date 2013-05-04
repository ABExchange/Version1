<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>LOGIN</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/exchange.css" media="all" rel="stylesheet" type="text/css"/>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.0.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/exchange.js"></script>
</head>
<body onload='document.f.j_username.focus();'>

 <div class="container">
  
  	<div class="row"><br/>
  			<div class="row"><br/>
  				<div class="span10"></div>
 				<div class="span2"> <span class="navigation-text"><span><a href="${pageContext.request.contextPath}/user/registration">Sign Up</a></span> | <span> <a href="${pageContext.request.contextPath}/user/login">Sign In</a></span> | <span> <a href="${pageContext.request.contextPath}/main">Home</a></span></span></div>  				
  			</div>
  			<div class="row">
  				<div class="span10">
  					<br/>
  					<span class="brand">Exchange System</span>
  					<ul class="nav">
   						<li><a href="${pageContext.request.contextPath}/trade">Trade</a></li>
  						<li> <a href="${pageContext.request.contextPath}/accounts">Accounts</a></li>
  						<li> <a href="${pageContext.request.contextPath}/markets">Markets</a></li>
  						<li> <a href="${pageContext.request.contextPath}/FAQ">FAQ</a></li>
  					</ul>
  				</div>
  				<div class="span2">
  					<br/>
  					<span id="price-button">PRICES</span>
  					
  				</div>
  			</div>
  	</div>
    
   
    <div class="row"><br/>
    	<div class="span8"> <br/>
    		<h1>LOGIN</h1> <br/>
    		<!-- Start of Middle Column -->
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='${pageContext.request.contextPath}/j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
				<td>Email:</td>
				<td><input type='text' name='j_username' value=''>
				<br/>
				<p>Email must be provided</p>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /> <br/>
				</td>
			</tr>
			<tr><td></td><td><span><a href="${pageContext.request.contextPath}/user/registration">Create Account</a> | <a href="${pageContext.request.contextPath}/user/forgotpassword">Forgot Password</a></span></td></tr>
			<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
			<tr>
				<td colspan='2' align="center"><input name="submit" type="submit"
					value="Login" class="btn btn-primary" /> <input name="reset" type="reset" value="Cancel" class="btn" />
				</td>
				<td colspan='2'>
				</td>
			</tr>
			<tr>
				
			</tr>
		</table>
 
	</form>
		<!-- End of Middle Column -->
    	</div>
    	
    	<div class="span4"> <br/>
    	
    	
    	
    	</div>
    
    </div>
    
    <div class="row">
    	<div class="span3 box">
    		<img  src="${pageContext.request.contextPath}/images/home_secure.jpg" alt="Secure Bitcoin Exchange"/>
    		<h3>SECURE</h3>
    		<p>
    		We've implemented strict security and enterprise infrastructure to keep your Bitcoins safe.
    		</p>
    		<a href="">LEARN MORE</a>
    	</div>
    	<div class="span3 box">
    		<img  src="${pageContext.request.contextPath}/images/home_simple.jpg" alt="Secure Bitcoin Exchange"/>
    		<p>
    		Just fund and enter your price. We'll automatically execute your trade at the best available price.
    		</p>
    		<h3>SIMPLE</h3>  
    		<a href="">LEARN MORE</a>  	
    	</div>
    	<div class="span3 box">
    		<img  src="${pageContext.request.contextPath}/images/home_affordable.jpg" alt="Secure Bitcoin Exchange"/>
    		<p>
    		We have a low 0.5% fee on all BTC trades regardless of volume, price, or currency. Reduce your spreads.
    		</p>
    		<h3>AFFORDABLE</h3>
    		<a href="">LEARN MORE</a>    	
    	</div>
    	<div class="span3 box">
    		<img  src="${pageContext.request.contextPath}/images/home_available.jpg" alt="Secure Bitcoin Exchange"/>
    		<p>
    		The freedom to make trades and use the platform all-day, everyday. No restrictions..
    		</p>
    		<h3>AVAILABLE 24/7</h3>
    		<a href="">LEARN MORE</a>    	
    	</div>
    
    </div>
    <div class="row">
    	<div class="span12">
    		<a>ABOUT US</a>&nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>FUNDING OPTION</a>&nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>TERMS OF USE</a>&nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>ROAD MAP</a> &nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>NEWS</a> &nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>CONTACT US</a>
    	</div>
    </div>
 	   
 </div>
</body>
</html>