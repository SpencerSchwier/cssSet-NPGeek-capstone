<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="park-detail">
	
	<c:url value="/parkDetail" var="parkDetailUrl" ></c:url>
	
	<form class= "fahr" method="POST" action="${parkDetailUrl}">
		<input type="hidden" name="temperature" value="fahrenheit">
  		<input type="submit" value="fahrenheit">
	</form>
	
	<form class= "celc" method="POST" action="${parkDetailUrl}">
  		<input type="hidden" name="temperature" value="celsius">
  		<input type="submit" value="celsius">
	</form>
	
	
	<c:url var="parkImgSrc" value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg"/>
	<img src="${parkImgSrc}" alt="parkPicture" class="bg"/>
	<div class= "acre">Acreage: ${park.acreage}</div>
	<div class= "elev">Elevation in Feet: ${park.elevationInFeet}</div>
	<div class= "trails">Miles of Trails: ${park.milesOfTrail}</div>
	<div class= "camps">Number of Campsites: ${park.numberOfCampsites}</div>
	<div class= "climate">Climate: ${park.climate}</div>
	<div class= "founded">Year Founded: ${park.yearFounded}</div>
	<div class= "visitors">Annual Visitors: ${park.annualVisitorCount}</div>
	<div class= "quote">Inspirational Quote: ${park.inspirationalQuote} ~ ${park.inspirationalQuoteSource}</div>
	<div class= "desc">${park.parkDescription}</div>
	<div class= "fee">Entry Fee: $${park.entryFee}</div>
	<div class= "anim">Number of Animal Species: ${park.numberOfAnimalSpecies}</div>
	<br>
</div>
	<br>
	<div class= "forecast-grid">
		<h1 class= "five-head" align="center">Five-Day Forecast</h1>
		
			<c:forEach var="day" items="${weather}" varStatus="status">
					<c:if test="${status.first}"> 
						<div class="today">Today</div>
						<c:url var="weatherImgSrc" value="/img/weather/${day.forecast}.png"/>
						<img class="todayImg" src="${weatherImgSrc}" alt="weatherPicture"/>
						<div class="todayL">Low: <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${day.low}"/> <%= session.getAttribute("temperature") %></div>
						<div class="todayH">High: <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${day.high}"/> <%= session.getAttribute("temperature") %></div>
						<div class = "f-text">Forecast: ${day.forecast}</div>
						<div class = "message">${day.message}. ${day.newMessage}.</div>
					</c:if>
					<c:if test="${!status.first}">
						<div id="fourDay">
						<c:url var="weatherImgSrc" value="/img/weather/${day.forecast}.png"/>
						<img class="imgDay${status.index}" src="${weatherImgSrc}" alt="weatherPicture"/>				
							<div class="high${status.index}">High: <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${day.high}"/> <%= session.getAttribute("temperature") %></div>
							<div class="low${status.index}">Low: <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${day.low}"/> <%= session.getAttribute("temperature") %></div>
						</div>		
					</c:if>
				
<%-- 				
				<div class="column">
					<c:url var="weatherImgSrc" value="/img/weather/${day.forecast}.png"/>
					<img class="todayImg" src="${weatherImgSrc}" alt="weatherPicture" style="width:60%"/>
					<div>High: <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${day.high}"/> <%= session.getAttribute("temperature") %></div>
			        <div>Low: <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${day.low}"/> <%= session.getAttribute("temperature") %></div>
			        <c:if test="${status.first}">
			        	<br>
			        	<div>${day.message}. ${day.newMessage}.</div>
			        	<div></div>
			        </c:if>
			        </div>
				 --%>
			
			</c:forEach>
	</div>

	





</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />