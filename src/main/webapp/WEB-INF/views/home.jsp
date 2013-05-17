<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Exchange System</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="all" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/exchange.css"
	media="all" rel="stylesheet" type="text/css" />
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
				<!-- div class="mainlogo">
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
  			</div -->
				<span> <img alt=""
					src="${pageContext.request.contextPath}/images/mainlogo.png" />
				</span>
				<!--span class="exchangeslogan">EASY SECURE RELIABLE</span -->
				<br /> <br /> <span><img alt=""
					src="${pageContext.request.contextPath}/images/mainlogowords.png">
				</span>
			</div>

			<div class="span3 mainlogincontainer">
				<div class="mainlogin">
					<div class="innerloginlogo">
						<span class="xlogoletter"> LOGIN </span>
						<hr />
						<form name='f'
							action="<c:url value='${pageContext.request.contextPath}/j_spring_security_check' />"
							method='POST'>
							<table>
								<tr>
									<td><input type='text' name='j_username' value=''
										placeholder="Email" /> <br /> <input type='password'
										name='j_password' placeholder="password" /></td>
									<!--td><input name="submit" type="submit"
					value="" class="btn btn-primary exchangebutton" /></td -->

								</tr>
								<tr>
									<td colspan='2' align="center"><input name="submit"
										type="submit" value="Login" class="btn btn-primary" /></td>
								</tr>
								<!-- btn btn-primary  -->
								<tr>
									<td><br /></td>
								</tr>
								<!-- tr>
				<td colspan='2' align="center"><input name="submit" type="submit"
					value="Login" class="btn btn-primary" /> <input name="reset" type="reset" value="Cancel" class="btn" />
				</td>
				<td colspan='2'>
				</td>
			</tr -->
							</table>

						</form>
					</div>
				</div>
				<span><a
					href="${pageContext.request.contextPath}/user/forgotpassword">forgot
						password?</a></span> <br /> <span>Not a member yet?<a
					href="${pageContext.request.contextPath}/user/registration">Register!</a></span>
			</div>
		</div>
		<!-- hr class="mainhr"/ -->
		<span> <img alt=""
			src="${pageContext.request.contextPath}/images/separatorline.png">
		</span>
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
			<div class="span4 box">
				<div class="imageholder">
					<img
						src="${pageContext.request.contextPath}/images/thebitcoincollection.png"
						alt="Secure Bitcoin Exchange" />
				</div>
				<div class="mainheaders">
					<div class="mainheadertext">
						<span><a href="${pageContext.request.contextPath}/trade">TRADE</a></span>
					</div>

				</div>
				<table border="1" class="maintabletext">
					<tr class="imageholderows">
						<td>BUY BITCOIN</td>
					</tr>
					<tr class="imageholderows">
						<td>SELL BITCOIN</td>
					</tr>
					<tr class="imageholderows">
						<td>TRADE HISTORY</td>
					</tr>
				</table>
			</div>
			<div class="span4 box">
				<div class="imageholder">
					<img
						src="${pageContext.request.contextPath}/images/realmarketstairs.png"
						alt="Secure Bitcoin Exchange" />
				</div>
				<div class="mainheaders">
					<div class="mainheadertext">
						<span><a href="${pageContext.request.contextPath}/markets">MARKET</a></span>
					</div>
				</div>
				<table border="1" class="maintabletext">
					<tr class="imageholderows">
						<td>LATEST TRADES</td>
					</tr>
					<tr class="imageholderows">
						<td>BUY ORDERS</td>
					</tr>
					<tr class="imageholderows">
						<td>SELL ORDERS</td>
					</tr>
				</table>
			</div>
			<div class="span4 box">
				<div class="imageholder">
					<img src="${pageContext.request.contextPath}/images/realfaqs.png"
						alt="Secure Bitcoin Exchange" />
				</div>
				<div class="mainheaders">
					<div class="mainheadertext">
						<span>FAQs</span>
					</div>
				</div>
				<table border="1" class="maintabletext">
					<tr class="imageholderows">
						<td>HOW DO I GET STARTED?</td>
					</tr>
					<tr class="imageholderows">
						<td>WHAT ARE YOUR FEES</td>
					</tr>
					<tr class="imageholderows">
						<td>MORE</td>
					</tr>
				</table>

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