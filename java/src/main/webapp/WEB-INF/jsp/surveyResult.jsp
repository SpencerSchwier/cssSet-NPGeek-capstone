<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1 style="text-align: center;">Survey Results</h1>

		<div id="result">
			<c:forEach var="park" items="${popularParks}">
				<div class="eachPark">
					<c:url var="parkPostHref" value="/parkDetail">
						<c:param name="id">${park.parkCode}</c:param>
					</c:url>
					<a class="vote-image" href="${parkPostHref}">
						<img src="<c:url value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />" />
					</a>
					<div class="park-vote">
					${park.parkName} <br>
					Votes: ${park.totalVotes}
					</div>
				</div>
			</c:forEach>
		</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />