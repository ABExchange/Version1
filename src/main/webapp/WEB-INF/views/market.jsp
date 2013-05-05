<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.*"%>
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
				<h1>MARKETS</h1>
				<br />
				<!-- Trade form was removed from here -->
				<ul class="nav nav-tabs">
					<li class="marketUSD active"><a href="">USD</a></li>
					<li class="marketCAD"><a href="">CAD</a></li>
					<li class="marketHistory"><a href="">Market History</a></li>
					<li class="allOpenOrders"><a href="">All Open Orders</a></li>
					<!-- li class="accountWithdrawal"><a href="">Withdraw </a></li>
					<li class="accountBankAccounts"><a href="">Bank Accounts </a></li>
					<li class="accountHistory"><a href="">History </a></li -->
				</ul>
				<div class="market marketUSD">
					<br /> <br />
					<h3>USD/BTC Rates</h3>
					<br />
					<div class="row">
						<div class="span4">
							<!-- Latest Trades Start-->
							<br />
							<h3 class="marketheader">LATES TRADES</h3>
							<br />
							<c:if test="${not empty listUSDTransaction}">

								<table class="table table-striped bordertable">
									<tr>
										<td class="highersell ">BTC</td>
										<td class="highersell ">PRICE/BTC</td>
										<td class="highersell ">TOTAL</td>
										<td class="highersell ">EXECUTED</td>

									</tr>

									<c:forEach items="${listUSDTransaction}" var="orderTransaction">
										<tr>
											<td><fmt:formatNumber pattern="###.####"
													value="${orderTransaction.price / orderTransaction.btcRate.rate} " /></td>
													
													
											<td><!-- c:out value="${orderTransaction.btcRate.rate}" /--> <fmt:formatNumber  pattern="###.####" value="${orderTransaction.btcRate.rate}" maxFractionDigits="4"/></td>
											<td><!-- c:out value="${orderTransaction.total}" / --> <fmt:formatNumber pattern="####.####" value="${orderTransaction.total}" maxFractionDigits="4"/>
												<!-- fmt:formatNumber pattern="###.##" value="${orderTransaction.total}" / -->
											</td>
											<td><c:out value="${orderTransaction.created}" /></td>
										</tr>
									</c:forEach>


								</table>
								<!-- Latest Trades End-->
							</c:if>
							<c:if test="${empty listUSDTransaction}">
								There are no Transactions yet. 
	  							</c:if>

						</div>

						<div class="span4">
							<!-- Buy Orders Start -->
							<br />
							<h3 class="marketheader">BUY ORDERS</h3>
							<br />

							<c:if test="${not empty listUSDBuyOrder}">
								<table class="table table-striped bordertable middletable">
									<tr>
										<td class="highersell">STATUS</td>
										<td class="highersell">PRICE</td>
										<td class="highersell">QTY</td>
										<td class="highersell">TOTAL</td>

									</tr>

									<c:forEach items="${listUSDBuyOrder}" var="tradeOrder">
										<tr>
										<td><c:out value="${tradeOrder.orderStatus}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:if>
							<c:if test="${empty listUSDBuyOrder}">
								There are no Buy Orders Yet. 
	  							</c:if>
						</div>

						<div class="span4">
							<!-- Buy Orders End -->

							<!-- Sell Orders Start -->
							<br />
							<h3 class="marketheader">SELL ORDERS</h3>
							<br />
							<c:if test="${not empty listUSDSellOrder}">

								<table class="table table-striped bordertable">
									<tr>
										<td class="highersell">STATUS</td>
										<td class="highersell">PRICE</td>
										<td class="highersell">QTY</td>
										<td class="highersell">TOTAL</td>

									</tr>

									<c:forEach items="${listUSDSellOrder}" var="tradeOrder">
										<tr>
											<td><c:out value="${tradeOrder.orderStatus}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:if>
							<c:if test="${empty listUSDSellOrder}">
								<table class="table table-striped bordertable">
									<tr>
										<td>There are no Sell Orders Yet</td>
									</tr>
								</table>
							</c:if>

						</div>

					</div>

					<!-- Sell Orders End -->



				</div>

				<div class="market marketCAD">
					<br /> <br />
					<!-- Start CAD -->
					<h3>CAD/BTC Rates</h3>
					<br />

					<div class="row rowmarket">
						<div class="span4">
							<!-- Latest Trades Start-->
							<br /> <br />
							<h3 class="marketheader">LATES TRADES</h3>
							<br />
							<c:if test="${not empty listCADTransaction}">

								<table class="table table-striped bordertable">
									<tr>
										<td class="highersell">BTC</td>
										<td class="highersell">PRICE/BTC</td>
										<td class="highersell">TOTAL</td>
										<td class="highersell">EXECUTED</td>

									</tr>

									<c:forEach items="${listCADTransaction}" var="orderTransaction">
										<tr>
											<td><fmt:formatNumber pattern="###.####"
													value="${orderTransaction.price / orderTransaction.btcRate.rate} " /></td>
											<!-- td><c:out value="${orderTransaction.btcRate.rate}" /></td>
											<td class="reducedtd"><c:out value="${orderTransaction.total}" /></td -->
											<td><!-- c:out value="${orderTransaction.btcRate.rate}" /--> <!-- fmt:formatNumber  pattern="###.##" value="${orderTransaction.btcRate.rate}" maxFractionDigits="4"/ --></td>
											<td><!-- c:out value="${orderTransaction.total}" / --> <fmt:formatNumber  pattern="###.####" value="${orderTransaction.total}" maxFractionDigits="4"/></td>
											
											<td><c:out value="${orderTransaction.created}" /></td>
										</tr>
									</c:forEach>


								</table>
								<!-- Latest Trades End-->
							</c:if>
							<c:if test="${empty listCADTransaction}">
								There are no Transactions yet. 
	  							</c:if>
						</div>
						<div class="span4">
							<!-- Buy Orders Start -->
							<br /> <br />
							<h3 class="marketheader">Buy Orders</h3>
							<br />

							<c:if test="${not empty listCADBuyOrder}">
								<table class="table table-striped bordertable middletable">
									<tr>
										<td class="highersell">STATUS</td>
										<td class="highersell">PRICE</td>
										<td class="highersell">QTY</td>
										<td class="highersell">TOTAL</td>

									</tr>

									<c:forEach items="${listCADBuyOrder}" var="tradeOrder">
										<tr>
											<td><c:out value="${tradeOrder.orderStatus}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:if>
							<c:if test="${empty listCADBuyOrder}">
								There are no Buy Orders Yet. 
	  							</c:if>
							<!-- Buy Orders End -->

						</div>
						<div class="span4">
							<!-- Sell Orders Start -->
							<br /> <br />
							<h3 class="marketheader">SELL Orders</h3>
							<br />
							<c:if test="${not empty listCADSellOrder}">

								<table class="table table-striped bordertable lasttable">
									<tr>
										<td class="higersell">STATUS</td>
										<td class="highersell">PRICE</td>
										<td class="highersell">QTY</td>
										<td class="highersell">TOTAL</td>

									</tr>

									<c:forEach items="${listCADSellOrder}" var="tradeOrder">
										<tr>
											<td><c:out value="${tradeOrder.orderStatus}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####" value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:if>
							<c:if test="${empty listCADSellOrder}">
								<table class="table table-striped bordertable lasttable">
									<tr>
										<td>There are no Sell Yet.</td>
									</tr>
								</table>
							</c:if>

							<!-- Sell Orders End -->

						</div>
					</div>



					<!-- End CAD -->
				</div>
				
				<!-- End Deposit -->
				
				<!-- Start Market History -->
				
				<div class="market marketHistory">
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
				<!-- End Market History -->
				
				<!-- Start All Open Orders -->
				<div class="market allOpenOrders">
					<br /> <br />
					<h3>All Open Orders</h3>
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
				<!-- End All Open Orders -->
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