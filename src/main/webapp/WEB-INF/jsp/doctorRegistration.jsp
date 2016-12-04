<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<br>
<br>
<br>
<div class="container">
	<div class="row">
		<form:form modelAttribute="user">
			<div class="col-sm-10">
				<div class="well well-sm" align="center">
					<strong>Doctor Registration Form</strong>
				</div>
				<table class="table table-bordered">
					<tr>
						<td><label for="UserName">Username</label></td>
						<td><spring:bind path="username">
								<input type="text" class="form-control" name="username"
									id="username" placeholder="Enter username" required>
							</spring:bind><p style="color: red;">${param['uniqueUsername']}</p></td>
					</tr>
					<tr>
						<td><label for="password">Password</label></td>
						<td><spring:bind path="password">
								<input type="password" class="form-control" name="password"
									id="password" placeholder="Enter password" required>
							</spring:bind></td>
					</tr>
					<tr>
						<td><label for="userEmail">Enter Email ID</label></td>
						<td><spring:bind path="userEmail">
								<input type="email" class="form-control" id="userEmail"
									name="userEmail" placeholder="Enter Email" required>
							</spring:bind><p style="color: red;">${param['uniqueEmailId']}</p></td>
					</tr>
					<spring:bind path="userType">
						<input type="hidden" class="form-control" name="userType"
							id="userType" value="doctor" disabled>
					</spring:bind>
					<tr>
						<td><label for="userAddress">Enter Address</label></td>
						<td><spring:bind path="userAddress">
								<input type="text" class="form-control" name="userAddress"
									id="userAddress" placeholder="Enter address" required>
							</spring:bind></td>
					</tr>
					<tr>
						<td><label for="dateOfBirth">Enter Date of Birth</label></td>
						<td><spring:bind path="dateOfBirth">
								<input type="date" class="form-control" name="dateOfBirth"
									id="dateOfBirth" placeholder="Enter date of birth" required>
							</spring:bind></td>
					</tr>
					<tr>
						<td><label for="userContact">Enter Contact</label></td>
						<td><spring:bind path="userContact">
								<input type="number" class="form-control" maxlength="10"
									minlength="10" name="userContact" id="userContact"
									placeholder="Enter Contact" required />
							</spring:bind></td>
					</tr>
				</table>
				<br> <input type="submit" name="submit" id="submit"
					class="btn btn-info">
			</div>
		</form:form>
	</div>
</div>
<br>
<br>