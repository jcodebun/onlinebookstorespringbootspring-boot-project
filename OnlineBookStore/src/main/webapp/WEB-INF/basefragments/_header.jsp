<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg navbar-light bg-footer ">

  <a class="navbar-brand" href="#">
   
   <b class="text-dark navbar-center">Online Book Store</b>
  </a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav justify-content-end">
			
			<%-- <c:if test="${sessionScope.user != null}">
				<span class="navbar-text">
					Hello,${sessionScope.user.firstName} (${sessionScope.user.roleName }) </span>
			</c:if> --%>
			<c:if test="${sessionScope.user == null}">
				
			</c:if>
			</ul>
			
		</div>
    
  </div>
</nav>

<nav class="navbar navbar-expand-lg navbar-secondary bg-header">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto navbar-center">
      <li class="nav-item active">
        <a class="nav-link" href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a>
      </li>
     
      
     <c:if test="${sessionScope.user != null}">
						<c:if test="${sessionScope.user.roleId == 1}">
								<li class="nav-item "><a class="nav-link "
								href="<c:url value="/home/login/addbook"/>">Add Books</a></li>
								<li class="nav-item "><a class="nav-link "
								href="<c:url value="/home/login/books/search"/>">Books</a></li>
						</c:if>
						<c:if test="${sessionScope.user.roleId == 2}">
							<li class="nav-item "><a class="nav-link "
								href="<c:url value="/home/login/bookedbook/search"/>">Booked Book List</a></li>
								<li class="nav-item "><a class="nav-link "
								href="<c:url value="/home/login/books/buy"/>">Buy More</a></li>
						</c:if>
						
       </c:if>
    </ul>
    
  </div>
  <ul class="navbar-nav justify-content-end ">
				<c:if test="${sessionScope.user != null}">
				
				
				<li class="nav-item "> <a class="nav-link" style="padding: 6px; color: black;">
					Hello,${sessionScope.user.firstName} (${sessionScope.user.roleName }) </a></li>
			

							 

					<li class="nav-item "><a class="nav-link"
						style="padding: 6px; color: black;" href="<c:url value="/home/login"/>">Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					 <li class="nav-item"><a class="nav-link"
						 href="<c:url value="/home/login"/>"><i class="fa fa-sign-in"> SignIn</i></a></li>
					 <li class="nav-item">
        				<a class="nav-link" href="<c:url value="/index"/>"><i class="fa fa-book">Books </i></a>
     				 </li>
					<%--<li class="nav-item "><a class="nav-link "
						 href="<c:url value="/home/donarSignUp"/>">Donar SignUp</a></li>
					<li class="nav-item "><a class="nav-link "
						 href="<c:url value="/home/ngoSignUp"/>">NGO SignUp</a></li>	  --%>
				</c:if>
			</ul>
</nav>
