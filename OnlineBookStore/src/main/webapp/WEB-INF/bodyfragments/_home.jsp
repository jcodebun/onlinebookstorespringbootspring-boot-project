<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/addbook" />
<c:url var="addSearch" value="/home/login/books/search" />
<c:url var="newUserUrl" value="/home/newUser?id=" />
<c:url var="deleteUrl" value="/home/login/books/delete?id=" />
<c:url var="imageUrl" value="/image?id=" />
<c:url var="pdfUrl" value="/home/pdf?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/books/search"
	modelAttribute="form">
<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />
		<sf:hidden path="id"/>
	<div class="container mt-4"
		style="position: relative; min-height: 100vh">
		<h2>Books</h2><hr/>
		<!-- cards start -->
		<div class="album py-5 bg-footer">
        <div class="container">
          <div class="row">
          <c:forEach items="${list}" var="book_list" varStatus="u">
            <div class="col-md-4">
              <div class="card mb-4 box-shadow">
                <img class="card-img-top" src="${imageUrl} ${book_list.id}" alt="Card image cap" width="200px" height="200px">
                <div class="card-body">
                  <b><p class="card-text text-dark">${book_list.bookName}</p></b>
                  <c:if test="${book_list.saleType=='Paid'}">
                  <h5 class="text-center">&#x20B9 ${book_list.price}</h5></c:if>
                  <c:if test="${book_list.saleType=='Free'}">
                  <h5 class="text-center">Free</h5></c:if>
                  <div class="d-flex justify-content-between align-items-center"> 
                  <c:if test="${book_list.saleType == 'Free' }">                  
                    <a class="btn btn-sm btn-danger" href="${pdfUrl}${book_list.id}" target="_blank">Download</a>
                  </c:if>
                  <c:if test="${book_list.saleType == 'Paid' }">                  
                    <a class="btn btn-sm btn-danger" href="${newUserUrl}${book_list.id}">Download</a>
                  </c:if>
                  </div>
                </div>
              </div>
            </div>
           </c:forEach> 
           </div>
          </div>
          </div>
            
		<!-- cards ends -->
		<br>
	<!-- Pagination Starts -->
	<nav aria-label="Page navigation example float-end">
				<ul class="pagination justify-content-end" style="font-size: 13px">
					<li class="page-item"><input type="submit" name="operation"
						class="page-link"
						<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
						value="Previous"></li>
					<c:forEach var="i" begin="1" end="${(listsize/10)+1}">
						<c:if test="${i== pageNo}">
							<li class="page-item active"><a class="page-link activate"
								href="${addSearch}?pageNo=${i}">${i}</a></li>
						</c:if>
						<c:if test="${i != pageNo}">
							<li class="page-item"><a class="page-link"
								href="${addSearch}?pageNo=${i}">${i}</a></li>
						</c:if>
					</c:forEach>
					<li class="page-item"><input type="submit" name="operation"
						class="page-link"
						<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
						value="Next"></li>
				</ul>
			</nav>
	<!-- Pagination Ends -->	
	</div>
</sf:form>		