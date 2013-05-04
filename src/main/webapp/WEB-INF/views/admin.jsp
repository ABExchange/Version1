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
			<h1>EXCHANGE ADMIN</h1>
			<br /> <br />
			<div class="span6">
				<br />
				<div>
					<form:form class="form-horizontal"
						action="${pageContext.request.contextPath}/symbol"
						modelAttribute="symbol">
						<!--  form class="form-horizontal"-->
						<fieldset>
							<legend>Symbol</legend>
							<div class="control-group">
								<label class="control-label" for="code">Code</label>
								<div class="controls">

									<form:input path="code" type="text" class="input-xlarge"
										id="code" name="code" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="description">Description</label>
								<div class="controls">
									<form:textarea path="description" class="input-xlarge"
										id="description" rows="3" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="code">Price</label>
								<div class="controls">

									<form:input path="price" type="text" class="input-xlarge"
										id="price" name="price" />
								</div>
							</div>


							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Add
									Symbol</button>
								<button class="btn">Cancel</button>
							</div>
						</fieldset>
					</form:form>
				</div>
				<legend>Symbols List</legend>

				<c:if test="${not empty symbolList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Code</th>
								<th>Description</th>
								<th>Price</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${symbolList}" var="symbol">
								<tr>
									<td><c:out value="${symbol.code}" /></td>
									<td><c:out value="${symbol.description}" /></td>
									<td><c:out value="${symbol.price}" /></td>
									<td><a href="symbol/${symbol.id}">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty symbolList}">
				There are no Symbols yet. 
	  </c:if>


				<!-- Registration form was removed from here -->
				<!-- table border="0">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th></th>
						</tr>
					</thead>
					<tr>
						<td>Japheth</td>
						<td>Odonya</td>
						<td>jodonya@gmail.com</td>
						<td>Delete</td>
					</tr>
					<tr>
						<td>Andrew</td>
						<td>Johnson</td>
						<td>johnson@gmail.com</td>
						<td>Delete</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table -->

				<!-- Start BTCRate -->

				<div>
					<form:form class="form-horizontal"
						action="${pageContext.request.contextPath}/btcrate"
						modelAttribute="btcRate">
						<!--  form class="form-horizontal"-->
						<fieldset>
							<legend>BTC Rate</legend>
							<div class="control-group">
								<label class="control-label" for="country">Account</label>
								<div class="controls">

									<form:select path="symbol.id">
										<form:option value="" label="--Please Select" />
										<form:options items="${symbolList}" itemValue="id"
											itemLabel="code" />
									</form:select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="rate">Rate</label>
								<div class="controls">

									<form:input path="rate" type="text" class="input-xlarge"
										id="rate" name="rate" />
								</div>
							</div>


							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Add Rate</button>
								<button class="btn">Cancel</button>
							</div>
						</fieldset>
					</form:form>
				</div>
				<legend>Rates List</legend>

				<c:if test="${not empty btcRateList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Account</th>
								<th>Rate</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${btcRateList}" var="btcRate">
								<tr>
									<td><c:out value="${btcRate.symbol}" /></td>
									<td><c:out value="${btcRate.rate}" /></td>
									<td><c:out value="${btcRate.current}" /></td>
									<td><a href="btcrate/${btcRate.id}">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty btcRateList}">
				There are no Rates yet. 
	  </c:if>

				<!-- End BTC Rate -->

			</div>

			<!-- div class="span4">
				<br />



			</div -->
			<!--  Start Add Country-->

			<div class="span6">
				<br />
				<div>
					<form:form class="form-horizontal"
						action="${pageContext.request.contextPath}/country"
						modelAttribute="country">
						<!--  form class="form-horizontal"-->
						<fieldset>
							<legend>Country</legend>
							<div class="control-group">
								<label class="control-label" for="code">Code</label>
								<div class="controls">

									<form:input path="code" type="text" class="input-xlarge"
										id="code" name="code" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="name">Name</label>
								<div class="controls">

									<form:input path="name" type="text" class="input-xlarge"
										id="name" name="name" />
								</div>
							</div>


							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Add
									Country</button>
								<button class="btn">Cancel</button>
							</div>
						</fieldset>
					</form:form>
				</div>
				<legend>Country List</legend>

				<c:if test="${not empty countryList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Code</th>
								<th>Name</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${countryList}" var="country">
								<tr>
									<td><c:out value="${country.code}" /></td>
									<td><c:out value="${country.name}" /></td>
									<td><a href="symbol/${country.id}">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty countryList}">
				There are no Countries yet. 
	  </c:if>
				<!--  End Country-->

				<!-- Start Transfer Method -->
				<br />
				<div>
					<form:form class="form-horizontal"
						action="${pageContext.request.contextPath}/transfermethod"
						modelAttribute="transferMethod">
						<!--  form class="form-horizontal"-->
						<fieldset>
							<legend>Transfer Method</legend>
							<div class="control-group">
								<label class="control-label" for="code">Name</label>
								<div class="controls">

									<form:input path="name" type="text" class="input-xlarge"
										id="code" name="name" />
								</div>
							</div>


							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Add
									Method</button>
								<button class="btn">Cancel</button>
							</div>
						</fieldset>
					</form:form>
				</div>
				<legend>Transfer Method List</legend>

				<c:if test="${not empty transferMethodList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Name</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${transferMethodList}" var="transferMethod">
								<tr>
									<td><c:out value="${transferMethod.name}" /></td>
									<td><a href="symbol/${transferMethod.id}">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty transferMethodList}">
				There are no Methods yet. 
	  </c:if>
				<!-- End Transfer Method -->
			</div>


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