
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.js"></script>
<script> 
$(function(){
	var btn=document.getElementById("submit");
	$('#prevPassword').keyup(function(){
		$.ajax('/myDentist/changePasswordAJAX.html',{
			cache:false,
			data: {
				prevPassword: $("#prevPassword").val(),
				userid : $("#userid").val()
            },
			success:function(data){
				$('#passCheck').html(data)
				if ($('#passCheck').val() == "Invalid Password" || $("#prevPassword").val()==''){
					$("#submit").attr("disabled", true);
				}else{
					$("#submit").removeAttr("disabled");
				}	
			}
		});
	});
});
</script>
<br>
<br>
<form action="/myDentist/users/changePassword.html" method="post"
	data-toggle="validator">
	<div align="center" style="height: 400px">
		<br />
		<div class="form-group">
			Previous Password : <input style="width: 400px;" type="password" id="prevPassword"
				class="form-control" placeholder="Enter previous Password"
				name="previousPassword"><div id="passCheck" style="color: red;"></div><br />
		</div>
		<div class="form-group">
			New Password : <input type="password" style="width: 400px;" data-minlength="6"
				class="form-control" name="inputPassword" id="inputPassword"
				placeholder="Password" required>
			<div class="help-block">Minimum of 6 characters</div>
		</div>
		<div class="form-group">
			<input type="password" style="width: 400px;" class="form-control"
				id="inputPasswordConfirm" data-match="#inputPassword"
				data-match-error="Whoops, these don't match" placeholder="Confirm"
				required>
			<div class="help-block with-errors"></div>
		</div>
		<br />
		<input type="submit" name="submit" id="submit" disabled="disabled"> 
		<input type="hidden" name="userid" id="userid" value="${userid}">
	</div>
</form>
<br>
<br>
