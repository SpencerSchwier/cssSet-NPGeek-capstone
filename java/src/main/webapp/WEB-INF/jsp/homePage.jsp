<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="parks">
	
	<h1 class="park-title">All Parks</h1>
	
	<c:forEach var="park" items="${parks}">
		<div class="home-wrapper">
		
			<div class="park-image">
				<c:url var="parkPostHref" value="/parkDetail">
					<c:param name="id">${park.parkCode}</c:param>
				</c:url>
				<a href="${parkPostHref}"><img src="<c:url value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />" /></a>
			</div>
			
			<div class="park-description">
				<p>Name: ${park.parkName}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Location: ${park.state}</p> 
				<p>Description: ${park.parkDescription}</p>
			</div>
		</div> 
	
	</c:forEach>
	
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
