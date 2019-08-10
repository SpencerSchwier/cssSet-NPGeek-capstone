<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<br>
<div>
<div class="surveytitle">
<h1>TAKE OUR SURVEY!</h1>
<p>Please fill out the following information. Upon submitting you will be redirected to your favorite park's page.</p>
</div>
</div>
	<c:url value="survey" var="surveyUrl"/>
	
	<form:form class="formS" action="${surveyUrl}" method="POST" modelAttribute="survey">
			<div class="favorite">
				<div>Favorite National Park</div>
				<form:select name="favoritePark" path="parkCode">
				<c:forEach var="park" items="${parks}">
					<option value="${park.parkCode}">${park.parkName}</option>
				</c:forEach>
				</form:select>
			</div>
			<br>
			<div class="email">
				<label for="emailAddress">Your Email:<br><br></label> 
				<form:input id="email" name="emailAddress" type="email" path="emailAddress"/>
				<form:errors path="emailAddress"/>
			</div>

			<br>
		<div class="state">	
			<div>Your state of residence:</div>
			<div>
				<form:select name = "stateOfResidence" path="state">
				<option value="AL">Alabama</option>
				<option value="AK">Alaska</option>
				<option value="AZ">Arizona</option>
				<option value="AR">Arkansas</option>
				<option value="CA">California</option>
				<option value="CO">Colorado</option>
				<option value="CT">Connecticut</option>
				<option value="DE">Delaware</option>
				<option value="DC">District Of Columbia</option>
				<option value="FL">Florida</option>
				<option value="GA">Georgia</option>
				<option value="HI">Hawaii</option>
				<option value="ID">Idaho</option>
				<option value="IL">Illinois</option>
				<option value="IN">Indiana</option>
				<option value="IA">Iowa</option>
				<option value="KS">Kansas</option>
				<option value="KY">Kentucky</option>
				<option value="LA">Louisiana</option>
				<option value="ME">Maine</option>
				<option value="MD">Maryland</option>
				<option value="MA">Massachusetts</option>
				<option value="MI">Michigan</option>
				<option value="MN">Minnesota</option>
				<option value="MS">Mississippi</option>
				<option value="MO">Missouri</option>
				<option value="MT">Montana</option>
				<option value="NE">Nebraska</option>
				<option value="NV">Nevada</option>
				<option value="NH">New Hampshire</option>
				<option value="NJ">New Jersey</option>
				<option value="NM">New Mexico</option>
				<option value="NY">New York</option>
				<option value="NC">North Carolina</option>
				<option value="ND">North Dakota</option>
				<option value="OH">Ohio</option>
				<option value="OK">Oklahoma</option>
				<option value="OR">Oregon</option>
				<option value="PA">Pennsylvania</option>
				<option value="RI">Rhode Island</option>
				<option value="SC">South Carolina</option>
				<option value="SD">South Dakota</option>
				<option value="TN">Tennessee</option>
				<option value="TX">Texas</option>
				<option value="UT">Utah</option>
				<option value="VT">Vermont</option>
				<option value="VA">Virginia</option>
				<option value="WA">Washington</option>
				<option value="WV">West Virginia</option>
				<option value="WI">Wisconsin</option>
				<option value="WY">Wyoming</option>
				</form:select>
			</div>
	</div>						
			<br>
			<div class="activity">
				<div>Activity Level: <br></div>
				<form:select name = "activityLevel" path="activityLevel">
				<option value="inactive">Inactive</option>
				<option value="sedentary">Sedentary</option>
				<option value="active">Active</option>
				<option value="veryActive">Very Active</option>
				<%-- <form:input type="radio" name="activity" value="inactive"> Inactive</form:input><br>
				<form:input type="radio" name="activity" value="sedentary"> Sedentary</form:input><br>
				<form:input type="radio" name="activity" value="active"> Active</form:input><br>
				<form:input type="radio" name="activity" value="veryActive"> Very Active</form:input><br> --%>
				</form:select>
			</div>	
				<br>
				<input class="submit" type="submit" name="submitSurvey" value="submit"/>
			
	</form:form>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />