<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Exchange System</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/exchange.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>

 <div class="container">
  
  	<div class="row"><br/>
  			<div class="row"><br/>
  				<div class="span10"></div>
  				<div class="span2"> <span class="navigation-text"><span>Sign Up</span> | <span class="pull-right">Sign In</span></span></div>
  				
  			</div>
  			<div class="row">
  				<div class="span10">
  					<br/>
  					<span class="brand">Exchange System</span>
  					<ul class="nav">
  						<li><a href="/Trade">Trade</a></li>
  						<li> <a href="/Accounts">Accounts</a></li>
  						<li> <a href="/Markets">Markets</a></li>
  						<li> <a href="/FAQ">FAQ</a></li>
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
    		<h1>The Exchange System</h1> <br/>
    		<span>Buy and Sell Currencies.</span>
    		
    		<img  src="${pageContext.request.contextPath}/images/bitcoin.jpg" alt="Secure Bitcoin Exchange"/>
    	</div>
    	
    	<div class="span4"> <br/>
    	
    	<form:form accept-charset="UTF-8" action="main" autocomplete="off" class="home-signup" modelAttribute="exchangeUser">
    	<div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="oIUnZW17rd+yyLcJHLyM6f+xx4VB+Xa9M5IonS42KKA=" /></div> 
    	  <dl class="form">
            <dd>
            	<form:input path="firstName"  type="text" name="firstName" class="textfield" placeholder="Provide First Name"  />
            </dd>
          </dl>
          
           <dl class="form">
            <dd>
            	<form:input path="lastName"  type="text" name="lastName" class="textfield" placeholder="Provide Last Name"/>
            </dd>
          </dl>

          <dl class="form">
            <dd>
              <form:input path="email"  type="text" name="email" class="textfield" placeholder="Your email" />
            </dd>
          </dl>

          <dl class="form">
            <dd>
              <form:input path="password" type="password" name="password" class="textfield" placeholder="Provide Password" />
              <p class="note">Tip: use at least one number and at least 7 characters.</p>
            </dd>
          </dl>

         

          <button class="btn btn-success" type="submit">Sign up for free</button>
</form:form>
    	
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
 	   
 </div>
</body>
</html>