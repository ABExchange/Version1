<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Exchange System - SIGN UP</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/exchange.css" media="all" rel="stylesheet" type="text/css"/>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.0.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/exchange.js"></script>
</head>
<body>

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
    		<h1>REGISTRATION</h1> <br/>
    		<form:form accept-charset="UTF-8" action="${pageContext.request.contextPath}/user/registration" autocomplete="off" class="home-signup" id="home-signup" modelAttribute="exchangeUser">
    	<div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="oIUnZW17rd+yyLcJHLyM6f+xx4VB+Xa9M5IonS42KKA=" /></div> 
    	  <dl class="form">
            <dd>
            	<form:input path="firstName"  type="text" name="firstName" class="textfield" id="textfield" placeholder="Provide First Name"  />
            	 <p class="registerwarning">First Name is Required!</p>
            </dd>
          </dl>
          
           <dl class="form">
            <dd>
            	<form:input path="lastName"  type="text" name="lastName" class="textfield" placeholder="Provide Last Name"/>
            	<p class="registerwarning">Last Name is Required!</p
            </dd>
          </dl>

          <dl class="form">
            <dd>
              <form:input path="email"  type="text" name="email" class="textfield" placeholder="Your email" />
              <p class="registerwarning">Email is Required!</p
            </dd>
          </dl>

          <dl class="form">
            <dd>
              <form:input path="password" type="password" name="password" class="textfield" id="signUpPass" placeholder="Provide Password" />
              <p class="registerwarning">Password is Required!</p <br/>
              <p class="note">Tip: use at least one number and at least 7 characters.</p>
            </dd>
            
          </dl>
          
          <dl class="form">
            <dd>
              <form:input path="passwordConfirm" type="password" name="passwordConfurm" class="textfield" id="signUpPassConfirm" placeholder="Password Confirmation" />
              <p class="registerwarning">Confirmation is Required!</p <br/>
              <p class="matchingvalues">Password and it's confirmation must match.</p>
            </dd>
          </dl>

          <!--  p class="registerwarning">Important fields are empty!</p -->

          <button class="btn btn-success signupbutton" type="submit">Sign up for free</button>
</form:form>
    		
    		
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