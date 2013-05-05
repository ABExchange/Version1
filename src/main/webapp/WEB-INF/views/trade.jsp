<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

		<div class="row">
			<br />
			<div class="row">
				<br />
				<div class="span9"></div>
				<div class="span3">
					<span class="navigation-text"><span>LOGGED IN AS <c:out
								value="${user}"></c:out>
					</span><br /> <span><a
							href="${pageContext.request.contextPath}/user/registration">Sign
								Up</a></span> | <span> <a
							href="${pageContext.request.contextPath}/user/login">Sign In</a></span>
						| <span> <a href="${pageContext.request.contextPath}/admin">Admin</a></span>
						| <span> <a href="${pageContext.request.contextPath}/main">Home</a></span></span>
				</div>

			</div>
			<div class="row">
				<div class="span10">
					<br /> <span class="brand">Exchange System</span>
					<ul class="nav">
						<li><a href="${pageContext.request.contextPath}/trade">Trade</a></li>
						<li><a href="${pageContext.request.contextPath}/accounts">Accounts</a></li>
						<li><a href="${pageContext.request.contextPath}/markets">Markets</a></li>
						<li><a href="${pageContext.request.contextPath}/FAQ">FAQ</a></li>
					</ul>
				</div>
				<div class="span2">
					<br /> <span id="price-button">PRICES</span>

				</div>
			</div>
		</div>


		<div class="row">
			<br />
			<div class="span12">
				<br />
				<h1>TRADE</h1>
				<br />
				<!-- Trade form was removed from here -->
				<ul class="nav nav-tabs">
					<li class="buybitcoins active"><a href="">Buy Bitcoins</a></li>
					<li class="sellbitcoins"><a href="">Sell Bitcoins</a></li>
					<li class="openorders"><a href="">Open Orders </a></li>
					<li class="tradehistory"><a href="">Trade History </a></li>
				</ul>
				<div class="trade buybitcoins">
					<form:form name='frmBuyBitcoin'
						action="${pageContext.request.contextPath}/trade/buybitcoin"
						modelAttribute="tradeOrder">

						<table class="table-styled">
							<tr>
								<td><h3>Bitcoin Buy Order</h3> <br /> <!-- select path="symbol.id">
				            		<option value="" label="--Please Select"/>
				            		<options items="${symbolList}" itemValue="id" itemLabel="code"/>
				        		</select --> <form:select path="symbol.id"
										class="textfieldbuy">
										<form:option value="" label="--Please Select" />
										<form:options items="${symbolList}" itemValue="id"
											itemLabel="code" />
									</form:select>
									<p class="registerwarning">Symbol is Required</p> <!-- select -->
									<!-- option value="" label="--Please Select"/ --> <!-- options items="${projectTypeList}" itemValue="id" itemLabel="name"/ -->
									<!--  options / --> <!-- option value="CAD">CAD</option>
										<option value="USD">USD</option>
								</select --></td>
								<td></td>
								<td></td>
								<td><table>
										<tr>
											<td>FEE RATE</td>
											<td>0.50%</td>
										</tr>
										<tr>
											<td>CAD AVAILABLE</td>
											<td>$0.00</td>
										</tr>
										<tr>
											<td>USD AVAILABLE</td>
											<td>$0.00</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td><span>Order Quantity*</span> <br /> <br /> <form:input
										type='text' name='orderQuantity' id="buyQuantity"
										class="textfieldbuy" value='${tradeOrder.quantity}'
										path="quantity" /> <br />
									<p class="registerwarning">Quantity must be specified</p></td>
								<td><span>Price*</span> <br /> <br /> <form:input
										type='text' name='price' id="buyPrice" class="textfieldbuy"
										value='127.9900' path="price" /> <br />
									<p class="registerwarning">There must be a price!</p></td>
								<td><span>Fee</span> <br /> <br /> <form:input
										type='text' name='fee' id="buyFee" class="textfieldbuy"
										value='${tradeOrder.fee}' path="fee" /> <br />
									<p class="registerwarning">There must be a fee!</p></td>
								<td><span>Total</span> <br /> <br /> <form:input
										type='text' name='total' id="buyTotal" class="textfieldbuy"
										value='${tradeOrder.total}' path="total" /> <br />
									<p class="registerwarning">There must be a total!</p></td>
							</tr>

							<tr>
								<td><button class="btn btn-success signupbuttonbuy"
										type="submit">Place Order</button></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


						</table>

					</form:form>
				</div>
				<!-- End Buy Bitcoin -->
				<!-- Start Sell BitCoin -->
				<div class="trade sellbitcoins">
					<form:form name='frmSellBitcoin'
						action="${pageContext.request.contextPath}/trade/sellbitcoin"
						modelAttribute="tradeOrder">

						<table>
							<tr>
								<td><h3>Bitcoin Sell Order</h3> <br /> <!--  select --> <!-- option value="" label="--Please Select"/ -->
									<!-- options items="${projectTypeList}" itemValue="id" itemLabel="name"/ -->
									<!--  options / --> <!--  option value="CAD">CAD</option>
										<option value="USD">USD</option>
								</select --> <form:select path="symbol.id" class="textfieldsell">
										<form:option value="" label="--Please Select" />
										<form:options items="${symbolList}" itemValue="id"
											itemLabel="code" />
									</form:select>
									<p class="registerwarning">Symbol is Required</p></td>
								<td></td>
								<td></td>
								<td><table>
										<tr>
											<td>FEE RATE</td>
											<td>0.50%</td>
										</tr>
										<tr>
											<td>BTC AVAILABLE</td>
											<td>$0.00</td>
										</tr>
										<tr>
											<td></td>
											<td></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td><span>Order Quantity*</span> <br /> <br /> <form:input
										type='text' name='orderQuantity' id="sellQuantity"
										class="textfieldsell" value='${tradeOrder.quantity}'
										path="quantity" /> <br />
									<p class="registerwarning">Quantity must be specified</p></td>

								<td><span>Price*</span> <br /> <br /> <form:input
										type='text' name='price' id="sellPrice" class="textfieldsell"
										value='127.9900' path="price" /> <br />
									<p class="registerwarning">Price is Required</p></td>
								<td><span>Fee</span> <br /> <br /> <input type='text'
									name='fee' id="sellFee" class="textfieldsell"
									value='${tradeOrder.fee}' path="fee" /> <br />
									<p class="registerwarning">Fee is Required</p></td>
								<td><span>Total</span> <br /> <br /> <input type='text'
									name='total' id="sellTotal" class="textfieldsell"
									value='${tradeOrder.total}' path="total" /> <br />
									<p class="registerwarning">Total Must be there</p></td>
							</tr>

							<tr>
								<td><button class="btn btn-success signupbuttonsell"
										type="submit">Place Order</button></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


						</table>

					</form:form>
				</div>
				<!--  End Sell Bitcoin-->

				<!-- Start Open Orders -->

				<div class="trade openorders">
					<br /> <br />
					<h3>Open Orders</h3>
					<br />
					<table class="table table-striped">
						<!-- thead>
							<tr>
								<th>ORDER TYPE</th>
								<th>PRICE</th>
								<th>CURRENCY</th>
								<th>QUANTITY</th>
								<th>TOTAL</th>
								<th>CREATED</th>
							</tr>
						</thead>
						<tbody -->
						<tr>
							<td class="highersell">ORDER TYPE</td>
							<td class="highersell">STATUS</td>
							<td class="highersell">PRICE</td>
							<td class="highersell">CURRENCY</td>
							<td class="highersell">ORDER QTY</td>
							<td class="highersell">UNFULFILLED QTY</td>
							<td class="highersell">TOTAL</td>
							<td class="highersell">CREATED</td>
							<td class="highersell"></td>
						</tr>
						<c:if test="${not empty listOrder}">
							<c:forEach items="${listOrder}" var="tradeOrder">
								<tr>
									<td><c:out value="${tradeOrder.orderType}" /></td>
									<td><c:out value="${tradeOrder.orderStatus}" /></td>
									<td><c:out value="${tradeOrder.price}" /></td>
									<td><c:out value="${tradeOrder.symbol}" /></td>
									<td><c:out value="${tradeOrder.quantity}" /></td>
									<td><c:out value="${tradeOrder.unfulfilledquantity}"></c:out></td>
									<td><c:out value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
									
									<td><fmt:formatDate value="${tradeOrder.created}" type="both" pattern="dd MMMM yyyy h:mm:ss" /></td>
									<td><a href="#">Delete</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty listOrder}">
						<tr><td>There are no Open Orders yet. </td></tr>
	  					</c:if>
						<!-- tr>
							<td>SELL</td>
							<td>127.00</td>
							<td>USD</td>
							<td>10</td>
							<td>12340</td>
							<td>24/04/2013</td>
						</tr>
						<tr>
							<td>SELL</td>
							<td>127.00</td>
							<td>KSH</td>
							<td>10</td>
							<td>12340</td>
							<td>24/04/2013</td>
						</tr -->
						<!--  --tbody -->
					</table>
				</div>
				<!-- End Open Orders -->
				<!-- Trade History -->
				<div class="trade tradehistory">
					<br /> <br />
					<h3>
						Trade History</h3> <br />
											<table class="table table-striped">
						<!-- thead>
							<tr>
								<th>ORDER TYPE</th>
								<th>PRICE</th>
								<th>CURRENCY</th>
								<th>QUANTITY</th>
								<th>TOTAL</th>
								<th>CREATED</th>
							</tr>
						</thead>
						<tbody -->
						<tr>
							<td class="highersell">ORDER TYPE</td>
							<td class="highersell">STATUS</td>
							<td class="highersell">PRICE</td>
							<td class="highersell">CURRENCY</td>
							<td class="highersell">QUANTITY</td>
							<td class="highersell">TOTAL</td>
							<td class="highersell">FEE</td>
							<td class="highersell">EXECUTED</td>
							<td class="highersell"></td>
						</tr>
						<c:if test="${not empty listOrderTransaction}">
							<c:forEach items="${listOrderTransaction}" var="orderTransaction">
								<tr>
									<td><c:out value="${orderTransaction.tradeOrder.orderType}" /></td>
									<td><c:out value="${orderTransaction.tradeOrder.orderStatus}" /></td>
									<td><c:out value="${orderTransaction.price}" /></td>
									<td><c:out value="${orderTransaction.symbol}" /></td>
									<td><c:out value="${orderTransaction.quantity}" /></td>
									<td><c:out value="${orderTransaction.total}" /></td>
									<td><c:out value="${orderTransaction.fee}" /></td>
									
									<td><fmt:formatDate value="${orderTransaction.created}" type="both" pattern="dd MMMM yyyy h:mm:ss" /></td>
									<td><a href="#">Delete</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty listOrderTransaction}">
						<tr><td>There are no Orders executed yet. </td></tr>
	  					</c:if>
						<!-- tr>
							<td>SELL</td>
							<td>127.00</td>
							<td>USD</td>
							<td>10</td>
							<td>12340</td>
							<td>24/04/2013</td>
						</tr>
						<tr>
							<td>SELL</td>
							<td>127.00</td>
							<td>KSH</td>
							<td>10</td>
							<td>12340</td>
							<td>24/04/2013</td>
						</tr -->
						<!--  --tbody -->
					</table>
				</div>
				<!-- End Trade History -->
			</div>

			<!--  div class="span2">
				<br />



			</div -->

		</div>

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
			<div class="span3 box">
				<img
					src="${pageContext.request.contextPath}/images/home_available.jpg"
					alt="Secure Bitcoin Exchange" />
				<p>The freedom to make trades and use the platform all-day,
					everyday. No restrictions..</p>
				<h3>AVAILABLE 24/7</h3>
				<a href="">LEARN MORE</a>
			</div>

		</div>
		<div class="row">
			<div class="span12">
				<a>ABOUT US</a>&nbsp;&nbsp; | &nbsp;&nbsp; <a>FUNDING OPTION</a>&nbsp;&nbsp;
				| &nbsp;&nbsp; <a>TERMS OF USE</a>&nbsp;&nbsp; | &nbsp;&nbsp; <a>ROAD
					MAP</a> &nbsp;&nbsp; | &nbsp;&nbsp; <a>NEWS</a> &nbsp;&nbsp; |
				&nbsp;&nbsp; <a>CONTACT US</a>
			</div>
		</div>

	</div>
</body>
</html>