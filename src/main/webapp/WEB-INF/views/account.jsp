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
				<h1>ACCOUNT</h1>
				<br />
				<!-- Trade form was removed from here -->
				<ul class="nav nav-tabs">
					<li class="accountBalance active"><a href="">Balance</a></li>
					<li class="accountDeposit"><a href="">Deposit</a></li>
					<li class="accountWithdrawal"><a href="">Withdraw </a></li>
					<li class="accountBankAccounts"><a href="">Bank Accounts </a></li>
					<li class="accountHistory"><a href="">History </a></li>
				</ul>
				<div class="account accountBalance">
					<br /> <br />
					<h3>Balance</h3>
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
							<td class="highersell">CURRENCY</td>
							<td class="highersell">BALANCE</td>
							<td class="highersell">AVAILABE</td>
							<td class="highersell">CREATED</td>

						</tr>

						<tr>
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
						</tr>

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
								<td><h3>Deposit</h3> <br /> <!-- select path="symbol.id">
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
						<br /> <br />
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
								<td><h3>Withdrawal</h3> <br /> <!-- select path="symbol.id">
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
								<td><button class="btn btn-success"
										type="submit">Submit</button></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


						</table>

					</form:form>

					<!-- Start Withdrawal Listing -->

					<div>
						<br /> <br />
						<h3>Withdrawals</h3>
						<br />
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

						<br /> <br />
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