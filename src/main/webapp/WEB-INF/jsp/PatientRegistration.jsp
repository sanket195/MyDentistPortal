<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script
  src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script> 
$(function(){
	$('#userEmail').keyup(function(){
		$.ajax('PatientRegistrationAJAX.html',{
			cache:false,
			data: {
				userEmail: $("#userEmail").val()
            },
			success:function(data){$('#emailcheck').html(data)}
		});
	});
	
	$('#username').keyup(function(){
		$.ajax('PatientRegistrationusernameAJAX.html',{
			cache:false,
			data: {
				userName: $("#username").val()
            },
			success:function(data){$('#usercheck').html(data)}
		});
	});
});
</script>
<div class="container">
    <div class="row" >
        <form:form modelAttribute="user">
            <div class="col-sm-10" >
                <div class="well well-sm" align="center"><strong>Patient Registration Form</strong></div>
                <table class="table table-bordered">
                	<tr>
                		<td><label for="UserName">Username</label></td>
                		<td><spring:bind path="username"><input type="text" class="form-control" name="username" id="username" placeholder="Enter username" required>
                    	</spring:bind><div id="usercheck" style="color: red;"></div></td>
                	</tr>
                	<tr>
                		<td><label for="password">Password</label></td>
                		<td><spring:bind path="password"><input type="password" class="form-control" name="password" id="password" placeholder="Enter password" required>
                    </spring:bind></td>
                	</tr>
                	<tr>
                		<td><label for="userEmail">Enter Email ID</label></td>
                		<td><div id="emailcheck" style="color: red;"></div><spring:bind path="userEmail"><input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="Enter Email" required>
                		</spring:bind></td>
                	</tr>
                	<tr>
                		<td><label for="userAddress">Enter Address</label></td>
                		<td><spring:bind path="userAddress"><input type="text" class="form-control" name="userAddress" id="userAddress" placeholder="Enter address" required>
                    </spring:bind></td>
                	</tr>
                	<spring:bind path="userType"><input type="hidden" class="form-control" name="userType" id="userType" value="patient" disabled>
                    </spring:bind>
                	<tr>
                		<td><label for="dateOfBirth">Enter Date of Birth</label></td>
                		<td><spring:bind path="dateOfBirth"><input type="date" class="form-control" name="dateOfBirth" id="dateOfBirth" placeholder="Enter date of birth" required>
                    </spring:bind></td>
                	</tr>
                	<tr>
                		<td><label for="userContact">Enter Contact</label></td>
                		<td><spring:bind path="userContact"><input type="number"  class="form-control" maxlength="10" minlength="10" data-fv-phone-country11="US" name="userContact" id="userContact" placeholder="Enter Contact" required/>
                    </spring:bind></td>
                	</tr>
                </table>
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info">
            </div>
        </form:form>
    </div>
</div>
<br><br>
