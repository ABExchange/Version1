<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<span>Hi! <c:out value="${user}"></c:out></span> <br /> <span>ACCOUNT NUMBER: <c:out value="${accountNo}"></c:out></span>
				 <br /> <span>MY WALLET : <fmt:formatNumber pattern="####.##" value="${btcBalance}" maxFractionDigits="2" /> BTC</span>
				<div>
			<div><span><img alt="" src="${pageContext.request.contextPath}/images/flagh.png"> </span>	<span  style="float: right;">HKD: $<fmt:formatNumber pattern="####.##" value="${hkdBalance}" maxFractionDigits="2" /></span></div><br/>
			<div><span><img alt="" src="${pageContext.request.contextPath}/images/flagc.png"> </span>	<span style="float: right;">RMB:  ¥<fmt:formatNumber pattern="####.##" value="${rmbBalance}" maxFractionDigits="2" /></span> </div><br/>	
			<div><span><img alt="" src="${pageContext.request.contextPath}/images/flagu.png"> </span>	<span  style="float: right;">USD: $<fmt:formatNumber pattern="####.##" value="${usdBalance}" maxFractionDigits="2" /></span></div>
			</div>	
				<span>
			<a href="/user/logout">
			<img src="${pageContext.request.contextPath}/images/reallogout.png" alt="Logout"/>
			</a>
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
			<div class="span2">
				<span class="tradebutton"> <a
					href="${pageContext.request.contextPath}/systemorders">All Orders History</a></span>
			</div>
		</div>
		<hr class="loggedinhr" />


		<div class="row">
			<br />
			<div class="span2">
				<div class="nav nav-tabs">
					<span class="accountBalance active"><a
						class="accountbuttons" href="">Balance</a></span> <br /> <span
						class="accountDeposit"><a class="accountbuttons" href="">Deposit</a></span>
					<br /> <span class="accountWithdrawal "><a
						class="accountbuttons" href="">Withdraw </a></span> <br /> <span
						class="accountBankAccounts"><a class="accountbuttons"
						href="">Bank Accounts </a></span> <br /> <span class="accountHistory"><a
						class="accountbuttons" href="">History </a></span> <br />
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
				<!--  h1>ACCOUNT</h1 -->
				<!-- Trade form was removed from here -->

				<div class="account accountBalance">
					<h3>Balance</h3>
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
							<td class="highersell">CURRENCY</td>
							<td class="highersell">BALANCE</td>
							<td class="highersell">AVAILABE</td>
							<td class="highersell">CREATED</td>

						</tr>
						
						<c:if test="${not empty listSymbolBalances}">
							
								<c:forEach items="${listSymbolBalances}" var="symbolBalance">
									<tr>
										<td><c:out value="${symbolBalance.symbol}" /></td>
										<td><fmt:formatNumber pattern="####.##" value="${symbolBalance.totalBalance}" maxFractionDigits="2" /></td>
										<td><fmt:formatNumber pattern="####.##" value="${symbolBalance.totalBalance}" maxFractionDigits="2" /></td>
										<td><fmt:formatDate value="${symbolBalance.symbol.created}" type="both" pattern="dd MMMM yyyy" /></td>
									</tr>
								</c:forEach>
						</c:if>

						<!--tr>
							<td>BTC</td>
							<td>0.000</td>
							<td>0.000</td>
							<td>April, 24 2013</td>
						</tr>

						<tr>
							<td>CAD</td>
							<td>0.000</td>
							<td>0.000</td>
							<td>April, 24 2013</td>
						</tr>
						<tr>
							<td>USD</td>
							<td>0.000</td>
							<td>0.000</td>
							<td>April, 24 2013</td>
						</tr -->

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
				<div class="account accountDeposit">
					<form:form name='frmDeposity'
						action="${pageContext.request.contextPath}/account/deposit"
						modelAttribute="deposit">

						<table class="table-styled">
							<tr>
								<td><h3>Deposit</h3> <!-- select path="symbol.id">
				            		<option value="" label="--Please Select"/>
				            		<options items="${symbolList}" itemValue="id" itemLabel="code"/>
				            		
				        		</select --> <span>Account*</span><br /> <form:select
										path="symbol.id" class="textfieldbuy">
										<form:option value="" label="--Please Select" />
										<form:options items="${symbolList}" itemValue="id"
											itemLabel="code" />
									</form:select>
									<p class="registerwarning">Symbol is Required</p> <!-- select -->
									<!-- option value="" label="--Please Select"/ --> <!-- options items="${projectTypeList}" itemValue="id" itemLabel="name"/ -->
									<!--  options / --> <!-- option value="CAD">CAD</option>
										<option value="USD">USD</option>
								</select --></td>
							</tr>
							<tr>

								<td><br /> <!-- select path="symbol.id">
				            		<option value="" label="--Please Select"/>
				            		<options items="${symbolList}" itemValue="id" itemLabel="code"/>
				            		
				        		</select --> <span>Transfer Method*</span><br /> <form:select
										path="transferMethod.id" class="textfieldbuy">
										<form:option value="" label="--Please Select" />
										<form:options items="${transferMethodList}" itemValue="id"
											itemLabel="name" />
									</form:select> <!-- p class="registerwarning">Transfer Method is Required</p -->
									<!-- select --> <!-- option value="" label="--Please Select"/ -->
									<!-- options items="${projectTypeList}" itemValue="id" itemLabel="name"/ -->
									<!--  options / --> <!-- option value="CAD">CAD</option>
										<option value="USD">USD</option>
								</select --></td>

							</tr>
							<tr>

								<td><span>Amount*</span> <br /> <br /> <form:input
										type='text' name='amount' id="amount" class="textfieldbuy"
										value='${deposit.amount}' path="amount" /> <br />
									<p class="registerwarning">Amount must be specified</p></td>
								<td></td>

							</tr>
							<tr>
								<td><button class="btn btn-success" type="submit">Submit</button></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


						</table>

					</form:form>

					<!-- Start Deposits Listing -->

					<div>
						<h3>Deposits</h3>
						<br />
						<c:if test="${not empty depositList}">
							<table class="table table-striped">
								<tr>
									<td class="highersell">Account</td>
									<td class="highersell">Transfer Method</td>
									<td class="highersell">Amount</td>
								</tr>
								<c:forEach items="${depositList}" var="deposit">
									<tr>
										<td><c:out value="${deposit.symbol}" /></td>
										<td><c:out value="${deposit.transferMethod}" /></td>
										<td><c:out value="${deposit.amount}" /></td>
										<td><a href="bank/${deposit.id}">Delete</a></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty depositList}">
				There are no Deposit yet. 
	  </c:if>
						<br />
					</div>
					<!-- End Deposit Listing -->
				</div>
				<!-- End Deposit -->

				<!-- Start of WithDrawal -->

				<div class="account accountWithdrawal">
					<form:form name='frmWithdrawal'
						action="${pageContext.request.contextPath}/account/withdrawal"
						modelAttribute="withdrawal">

						<table class="table-styled">
							<tr>
								<td><h3>Withdrawal</h3> <!-- select path="symbol.id">
				            		<option value="" label="--Please Select"/>
				            		<options items="${symbolList}" itemValue="id" itemLabel="code"/>
				            		
				        		</select --> <span>Account*</span> <form:select
										path="symbol.id" class="textfieldbuy">
										<form:option value="" label="--Please Select" />
										<form:options items="${symbolList}" itemValue="id"
											itemLabel="code" />
									</form:select>
									<p class="registerwarning">Symbol is Required</p> <!-- select -->
									<!-- option value="" label="--Please Select"/ --> <!-- options items="${projectTypeList}" itemValue="id" itemLabel="name"/ -->
									<!--  options / --> <!-- option value="CAD">CAD</option>
										<option value="USD">USD</option>
								</select --></td>
							</tr>
							<tr>
								<td><span>Amount*</span> <br /> <br /> <form:input
										type='text' name='amount' id="amount" class="textfieldbuy"
										value='${withdrawal.amount}' path="amount" /> <br />
									<p class="registerwarning">Amount must be specified</p></td>

							</tr>
							<tr>
								<td><span>Bitcoin Address</span> <br /> <br /> <form:input
										type='text' name='bitcoinaddress' id="bitcoinaddress"
										class="textfieldbuy" value='${withdrawal.bitcoinaddress}'
										path="bitcoinaddress" /> <br />
									<p class="registerwarning">Amount must be specified</p></td>
								<td></td>

							</tr>
							<tr>
								<td><button class="btn btn-success" type="submit">Submit</button></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


						</table>

					</form:form>

					<!-- Start Withdrawal Listing -->

					<div>
						<h3>Withdrawals</h3>
						<c:if test="${not empty withdrawalList}">
							<table class="table table-striped">
								<tr>
									<td class="highersell">Account</td>
									<td class="highersell">Amount</td>
									<td class="highersell">Bitcoin Address</td>
								</tr>
								<c:forEach items="${withdrawalList}" var="withdrawal">
									<tr>
										<td><c:out value="${withdrawal.symbol}" /></td>
										<td><c:out value="${withdrawal.amount}" /></td>
										<td><c:out value="${withdrawal.bitcoinaddress}" /></td>
										<td><a href="bank/${withdrawal.id}">Delete</a></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty withdrawalList}">
				There are no Withdrawals yet. 
	  				</c:if>
						<br />
					</div>
					<!-- End Withdrawal Listing -->
				</div>
				<!-- End of Withdrawal -->

				<!-- Start Bank Accounts -->
				<div class="account accountBankAccounts">
					<div>

						<div>
							<h3></h3>
							<span><a id="bankAddToggle" class="menubutton"
								style="float: right;" href="#">Close</a></span>
						</div>
						<div id="bankAdd">
							<br />
							<form:form class="form-horizontal"
								action="${pageContext.request.contextPath}/addBank"
								modelAttribute="bank">
								<!--  form class="form-horizontal"-->
								<fieldset>
									<legend>Add Bank Account</legend>
									<table>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="code">Account
														Name</label>
													<div class="controls">
														<form:input path="accountname" type="text"
															class="input-xlarge" id="accountname" name="accountname" />
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="branchAddress">Branch
														Address</label>
													<div class="controls">
														<form:textarea path="branchAddress" class="input-xlarge"
															id="branchAddress" rows="3" />
													</div>
												</div>
											</td>
										</tr>

										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="country">Country</label>
													<div class="controls">
														<!--  select id="Country">  
					                <option>Select Project Type</option>  
					                <option>Software Development</option>  
					                <option>Heavy Machinery</option>  
					                <option>Networking</option>  
					                <option>Hardware Maintenance</option>  
					              </select -->
														<form:select path="country.id">
															<form:option value="" label="--Please Select" />
															<form:options items="${countryList}" itemValue="id"
																itemLabel="name" />
														</form:select>
													</div>
												</div>
											</td>

											<td><div class="control-group">
													<label class="control-label" for="branchPhoneNo">Branch
														Phone #</label>
													<div class="controls">

														<form:input path="branchPhoneNo" type="text"
															class="input-xlarge" id="branchPhoneNo"
															name="branchPhoneNo" />
													</div>
												</div></td>
										</tr>


										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="bankName">Bank
														Name</label>
													<div class="controls">

														<form:input path="bankName" type="text"
															class="input-xlarge" id="bankName" name="bankName" />
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="swiftCode">Swift
														Code</label>
													<div class="controls">

														<form:input path="swiftCode" type="text"
															class="input-xlarge" id="swiftCode" name="swiftCode" />
													</div>
												</div>
											</td>
										</tr>


										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="symbol">Account
														Type</label>
													<div class="controls">
														<!--  select id="Country">  
					                <option>Select Project Type</option>  
					                <option>Software Development</option>  
					                <option>Heavy Machinery</option>  
					                <option>Networking</option>  
					                <option>Hardware Maintenance</option>  
					              </select -->
														<form:select path="symbol.id">
															<form:option value="" label="--Please Select" />
															<form:options items="${symbolList}" itemValue="id"
																itemLabel="code" />
														</form:select>
													</div>
												</div>

											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="iban">IBAN #
														(International Only)</label>
													<div class="controls">

														<form:input path="iban" type="text" class="input-xlarge"
															id="iban" name="iban" />
													</div>
												</div>
											</td>
										</tr>

										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="acountNo">Account
														Number *</label>
													<div class="controls">

														<form:input path="acountNo" type="text"
															class="input-xlarge" id="acountNo" name="acountNo" />
													</div>
												</div>
											</td>
											<td></td>
										</tr>

										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="institutionId">Institution
														ID (3 or 4 digits)</label>
													<div class="controls">

														<form:input path="institutionId" type="text"
															class="input-xlarge" id="institutionId"
															name="institutionId" />
													</div>
												</div>
											</td>
											<td></td>
										</tr>


										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="bankTransitNo">Bank
														Transit No</label>
													<div class="controls">

														<form:input path="bankTransitNo" type="text"
															class="input-xlarge" id="bankTransitNo"
															name="bankTransitNo" />
													</div>
												</div>
											</td>
											<td></td>
										</tr>

										<tr>
											<td><div class="form-actions">
													<button type="submit" class="btn btn-primary">Add
														Bank</button>
												</div></td>
											<td>
												<div class="form-actions">
													<button class="btn">Cancel</button>
												</div>
											</td>
										</tr>
									</table>


								</fieldset>
							</form:form>
						</div>

					</div>
					<!-- Begin Bank Listing -->
					<div id="bankListing">
						<br /> <br />
						<h3>Bank Accounts</h3>
						<br />
						<c:if test="${not empty bankList}">
							<table class="table table-striped">
								<tr>
									<td class="highersell">Bank</td>
									<td class="highersell">Account Type</td>
									<td class="highersell">Account Number</td>
								</tr>
								<c:forEach items="${bankList}" var="bank">
									<tr>
										<td><c:out value="${bank.bankName}" /></td>
										<td><c:out value="${bank.symbol}" /></td>
										<td><c:out value="${bank.acountNo}" /></td>
										<td><a href="bank/${bank.id}">Delete</a></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty bankList}">
				There are no Banks yet. 
	  </c:if>
						<br />
					</div>
					<!-- End Bank Listing -->
				</div>
				<!--  End Bank Accounts-->

				<!-- Start History -->
				<div class="account accountHistory">
					<br /> <br />
					<h3>History</h3>
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
							<td class="highersell">TYPE</td>
							<td class="highersell">METHOD</td>
							<td class="highersell">AMOUNT</td>
							<td class="highersell">CURRENCY</td>
							<td class="highersell">STATUS</td>
							<td class="highersell">INITIATED</td>
							<td class="highersell">FEE</td>


						</tr>

						<!-- tr>
									<td>BTC</td>
									<td>0.000</td>
									<td>0.000</td>
									<td>0.000</td>
									<td>April, 24 2013</td>
								</tr>
							
								<tr>
									<td>CAD</td>
									<td>0.000</td>
									<td>0.000</td>
									<td>0.000</td>
									<td>April, 24 2013</td>
								</tr>
								<tr>
									<td>USD</td>
									<td>0.000</td>
									<td>0.000</td>
									<td>0.000</td>
									<td>April, 24 2013</td>
								</tr -->

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

	</div>
</body>
</html>