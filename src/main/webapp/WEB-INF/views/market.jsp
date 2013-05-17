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
				<span> <img alt=""
					src="${pageContext.request.contextPath}/images/mainlogo.png" />
				</span>
				<!--span class="exchangeslogan">EASY SECURE RELIABLE</span -->
				<br /> <br /> <span><img alt=""
					src="${pageContext.request.contextPath}/images/mainlogowords.png">
				</span>
			</div>

			<div class="span6 mainlogincontainer mainlogincontainertextalign">
				<span>Hi! <c:out value="${user}"></c:out></span> <br /> <span>ACCOUNT
					NUMBER: XXXXXXXXXX</span> <br /> <span>MY WALLET : XXXXXXX BTC</span>
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
		<hr class="mainhr" />
		<div class="row">
			<div class="span2">
				<span class="tradebutton"><a
					href="${pageContext.request.contextPath}/accounts">ACCOUNT</a></span>
			</div>
			<div class="span2">
				<span class="tradebutton"><a
					href="${pageContext.request.contextPath}/trade">TRADE</a></span>
			</div>
			<div class="span2">
				<span class="tradebutton"><a
					href="${pageContext.request.contextPath}/markets">MARKET</a></span>
			</div>
			<div class="span2">
				<span class="tradebutton"> <a
					href="${pageContext.request.contextPath}/FAQ">FAQ</a></span>
			</div>
			<div class="span2">
				<span class="tradebutton"> <a
					href="${pageContext.request.contextPath}/admin">ADMIN</a></span>
			</div>
		</div>
		<hr class="loggedinhr" />


		<div class="row">
			<br />
			<div class="span2">
				<div class="nav nav-tabs">
					<span class="marketUSD active"><a class="accountbuttons"
						href="">USD</a></span> <br /> <span class="marketCAD"><a
						class="accountbuttons" href="">CAD</a></span> <br /> <span
						class="marketHistory "><a class="accountbuttons" href="">Market
							History </a></span> <br />
					<!--span class="allOpenOrders"><a class="accountbuttons" href="">All Open Orders </a></span> <br/ -->
				</div>

				<!-- ul class="nav nav-tabs">
					<li class="accountBalance active"><a href="">Balance</a></li>
					<li class="accountDeposit"><a href="">Deposit</a></li>
					<li class="accountWithdrawal"><a href="">Withdraw </a></li>
					<li class="accountBankAccounts"><a href="">Bank Accounts </a></li>
					<li class="accountHistory"><a href="">History </a></li>
				</ul -->
			</div>
			<div class="span10">
				<!-- Trade form was removed from here -->
				<div class="market marketUSD">
					<h3>USD/BTC Rates</h3>
					<div class="row">
						<div class="span3">
							<!-- Latest Trades Start-->
							<h3 class="marketheader">LATES TRADES</h3>
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


											<td>
												<!-- c:out value="${orderTransaction.btcRate.rate}" /--> <fmt:formatNumber
													pattern="###.####" value="${orderTransaction.btcRate.rate}"
													maxFractionDigits="4" />
											</td>
											<td>
												<!-- c:out value="${orderTransaction.total}" / --> <fmt:formatNumber
													pattern="####.####" value="${orderTransaction.total}"
													maxFractionDigits="4" /> <!-- fmt:formatNumber pattern="###.##" value="${orderTransaction.total}" / -->
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

						<div class="span3">
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
											<td><fmt:formatNumber pattern="###.####"
													value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####"
													value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:if>
							<c:if test="${empty listUSDBuyOrder}">
								There are no Buy Orders Yet. 
	  							</c:if>
						</div>

						<div class="span3">
							<!-- Buy Orders End -->

							<!-- Sell Orders Start -->

							<h3 class="marketheader">SELL ORDERS</h3>
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
											<td><fmt:formatNumber pattern="###.####"
													value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####"
													value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
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
					<!-- Start CAD -->
					<h3>CAD/BTC Rates</h3>
					<div class="row rowmarket">
						<div class="span3">
							<!-- Latest Trades Start-->
							<h3 class="marketheader">LATES TRADES</h3>
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
											<td>
												<!-- c:out value="${orderTransaction.btcRate.rate}" /--> <!-- fmt:formatNumber  pattern="###.##" value="${orderTransaction.btcRate.rate}" maxFractionDigits="4"/ -->
											</td>
											<td>
												<!-- c:out value="${orderTransaction.total}" / --> <fmt:formatNumber
													pattern="###.####" value="${orderTransaction.total}"
													maxFractionDigits="4" />
											</td>

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
						<div class="span3">
							<!-- Buy Orders Start -->
							<h3 class="marketheader">Buy Orders</h3>
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
											<td><fmt:formatNumber pattern="###.####"
													value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####"
													value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
										</tr>
									</c:forEach>

								</table>
							</c:if>
							<c:if test="${empty listCADBuyOrder}">
								There are no Buy Orders Yet. 
	  							</c:if>
							<!-- Buy Orders End -->

						</div>
						<div class="span3">
							<!-- Sell Orders Start -->
							<h3 class="marketheader">SELL Orders</h3>
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
											<td><fmt:formatNumber pattern="###.####"
													value="${tradeOrder.price}" /></td>
											<td><c:out value="${tradeOrder.unfulfilledquantity}" /></td>
											<td><fmt:formatNumber pattern="###.####"
													value="${(tradeOrder.price * tradeOrder.unfulfilledquantity) + (tradeOrder.price * tradeOrder.unfulfilledquantity*0.005)}" /></td>
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
					<h3>Trade History</h3>
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
							<!-- td class="highersell">STATUS</td -->
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
									<td><c:out
											value="${orderTransaction.tradeOrder.orderType}" /></td>
									<!-- td><c:out value="${orderTransaction.tradeOrder.orderStatus}" /></td -->
									<td><c:out value="${orderTransaction.price}" /></td>
									<td><c:out value="${orderTransaction.symbol}" /></td>
									<td><c:out value="${orderTransaction.quantity}" /></td>
									<td><c:out value="${orderTransaction.total}" /></td>
									<td><c:out value="${orderTransaction.fee}" /></td>

									<td><fmt:formatDate value="${orderTransaction.created}"
											type="both" pattern="dd MMMM yyyy h:mm:ss" /></td>
									<td><a href="#">Delete</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty listOrderTransaction}">
							<tr>
								<td>There are no Orders executed yet.</td>
							</tr>
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
				<!--div class="market allOpenOrders">
					<br /> <br />
					<h3>All Open Orders</h3>
					<br />
					<table class="table table-striped">

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

					</table>
				</div -->
				<!-- End All Open Orders -->
			</div>

			<!--  div class="span2">
				<br />



			</div -->

		</div>

	</div>
</body>
</html>