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
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.0.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/exchange.js"></script>
</head>
<body>

 <div class="container">
  	<div class="row bitcoinprices">
  		<div class="span12">
				<span>LAST PRICE : $${lastPrice}</span> <span>HIGH :
					$${highPrice}</span> <span>LOW : $${lowPrice}</span> <span>VOLUME :
					${volume}</span> <span>AVG : $${average}</span>
				<div style="float: right;">
					<!--form>
  				<select class="textfieldcurrency">
					<option value="USD" label="USD">USD</option>
					<option value="CAD" label="CAD">CAD</option>
				</select>
			</form -->
					<form:form name='frmTradeStatus'
						action="${pageContext.request.contextPath}/home/${tradeStatus.symbol.id}"
						modelAttribute="tradeStatus">
						<form:select class="textfieldcurrency" path="symbol.id" name="symbols" onchange="this.form.submit();">
							<form:option value="" label="--Please Select" />
							<form:options items="${listSymbol}" itemValue="id"
								itemLabel="code" />
						</form:select>
					</form:form>
				</div>
			</div>
  	</div>
  	<div class="row">
  		<div class="span3 mainlogocontainer">
  			<!--div class="mainlogo">
  			<div class="innermainlogo">
  			<div style="padding-top: 30px">
  			<span class="logoletters">
  				ABE
  			</span>
  			<span class="xlogoletter">X</span>
  			<br/>
  			<br/>
  			<span>
  				www.abexhange.net
  			</span>
  			</div>
  			</div>
  			</div>
  			<span class="exchangeslogan">EASY SECURE RELIABLE</span -->
  			<span>
  				<img alt="" src="${pageContext.request.contextPath}/images/mainlogo.png"/>
  			</span>
  			<!--span class="exchangeslogan">EASY SECURE RELIABLE</span -->
  			<br/>
  			<br/>
  			<span><img alt="" src="${pageContext.request.contextPath}/images/mainlogowords.png">   </span>
  			
  		</div> 
  		
  		<div class="span6 mainlogincontainer mainlogincontainertextalign">
			<span>Hi! <c:out value="${user}"></c:out></span>
			<br/>
			<span>ACCOUNT NUMBER: XXXXXXXXXX</span>
			<br/>
			<span>MY WALLET : XXXXXXX BTC</span>
			<div>
			<div><span><img alt="" src="${pageContext.request.contextPath}/images/flagh.png"> </span>	<span  style="float: right;">HKD: $XXXXX.xx</span></div><br/>
			<div><span><img alt="" src="${pageContext.request.contextPath}/images/flagc.png"> </span>	<span style="float: right;">RMB: $XXXXX.xx</span> </div><br/>	
			<div><span><img alt="" src="${pageContext.request.contextPath}/images/flagu.png"> </span>	<span  style="float: right;">USD: $XXXXX.xx</span></div>
			</div>	
			<span>
			<img src="${pageContext.request.contextPath}/images/reallogout.png" alt="Logout"/>
			</span>	
  		</div>
  	</div>
  	<hr class="mainhr"/>
  	<div class="row">
  		<div class="span2"><span class="tradebutton"><a href="${pageContext.request.contextPath}/accounts">ACCOUNT</a></span></div>
 		<div class="span2"><span class="tradebutton"><a href="${pageContext.request.contextPath}/trade">TRADE</a></span></div>
 		<div class="span2"><span class="tradebutton"><a href="${pageContext.request.contextPath}/markets">MARKET</a></span></div>
 		<div class="span2"><span class="tradebutton"> <a href="${pageContext.request.contextPath}/FAQ">FAQ</a></span></div>
 		<div class="span2"><span class="tradebutton"> <a href="${pageContext.request.contextPath}/admin">ADMIN</a></span></div> 
  	</div>
  	<hr class="loggedinhr"/>
  	<!-- div class="row"><br/>
  			<div class="row"><br/>
  				<div class="span9"></div>
  				<div class="span3"> <span class="navigation-text"><span>LOGGED IN AS <c:out value="${user}"></c:out> </span><br/><span><a href="${pageContext.request.contextPath}/user/registration">Sign Up</a></span> | <span> <a href="${pageContext.request.contextPath}/user/login">Sign In</a></span>
  				| <span> <a href="${pageContext.request.contextPath}/admin">Admin</a></span> | <span> <a href="${pageContext.request.contextPath}/main">Home</a></span></span></div>
  				
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
  	</div -->
    
   
    <!-- div class="row"><br/>
    	<div class="span8"> <br/>
    		<h1>The Exchange System</h1> <br/>
    		<span>Buy and Sell Currencies.</span>
    		
    		<img  src="${pageContext.request.contextPath}/images/bitcoin.jpg" alt="Secure Bitcoin Exchange"/>
    	</div>
    	
    	<div class="span4"> <br/>
    	
    	< Registration form was removed from here -->
    	
    	<!--  /div>
    
    </div -->
    
 <div class="row">
			<div class="span3 box">
				<img src="${pageContext.request.contextPath}/images/home_secure.jpg"
					alt="Secure Bitcoin Exchange" />
				<h3>SECURE</h3>
				<p>We've implemented strict security and enterprise
					infrastructure to keep your Bitcoins safe.</p>
				<a href="">LEARN MORE</a>
			</div>
			<div class="span3 box">
				<img src="${pageContext.request.contextPath}/images/home_simple.jpg"
					alt="Secure Bitcoin Exchange" />
				<p>Just fund and enter your price. We'll automatically execute
					your trade at the best available price.</p>
				<h3>SIMPLE</h3>
				<a href="">LEARN MORE</a>
			</div>
			<div class="span3 box">
				<img
					src="${pageContext.request.contextPath}/images/home_affordable.jpg"
					alt="Secure Bitcoin Exchange" />
				<p>We have a low 0.5% fee on all BTC trades regardless of
					volume, price, or currency. Reduce your spreads.</p>
				<h3>AFFORDABLE</h3>
				<a href="">LEARN MORE</a>
			</div>
		</div>
    <!--div class="row">
    	<div class="span12">
    		<a>ABOUT US</a>&nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>FUNDING OPTION</a>&nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>TERMS OF USE</a>&nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>ROAD MAP</a> &nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>NEWS</a> &nbsp;&nbsp; | &nbsp;&nbsp;
    		<a>CONTACT US</a>
    	</div>
    </div -->
 	   
 </div>
</body>
</html>