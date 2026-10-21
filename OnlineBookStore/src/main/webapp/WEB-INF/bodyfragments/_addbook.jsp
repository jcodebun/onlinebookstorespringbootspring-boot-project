<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<section class="text-center ">
<div class="card mb-3 bg-header" style="width: 50%">
    <div class="row g-0 d-flex align-items-center">
    
      <div class="col-lg-12">
        <div class="card-body py-5 px-md-5">

          <sf:form method="post" action="${pageContext.request.contextPath}/home/login/addbook" modelAttribute="form" enctype="multipart/form-data"><br/>
             <h2>Add Books</h2><hr/>
             	<sf:hidden path="id"/>
             <b><%@ include file="businessMessage.jsp"%></b>
             <div class="form-outline mb-4 ">
            <s:bind path="bookName">
						<label for="inputEmail4" class="form-label">Book Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
              
            </div>

            <div class="form-outline mb-4">
             <s:bind path="saleType">
						<label for="inputEmail4" class="form-label">Sale Type</label>
						<sf:select id="one" class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:options   items="${saleType}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
             <div class="form-outline mb-4">
             <s:bind path="price">
						<label for="inputEmail4" class="form-label">Price</label>
						<sf:input id="two" path="${status.expression}"
							placeholder="Enter price" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            <div class="form-outline mb-4">
             <s:bind path="bookImage">
						<label for="inputEmail4" class="form-label">Book Image</label>
						<sf:input type="file" path="${status.expression}"
							placeholder="Upload Book Image" class="form-control" required="required"/>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            <div class="form-outline mb-4">
             <s:bind path="bookPdf">
						<label for="inputEmail4" class="form-label">Book PDF</label>
						<sf:input type="file" path="${status.expression}"
							placeholder="Upload Book PDF" class="form-control" required="required" accept="application/pdf"/>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            <div class="form-outline mb-4">
             <s:bind path="bookDescription">
						<label for="inputEmail4" class="form-label">Description</label>
						<sf:textarea path="${status.expression}"
							placeholder="Enter book description" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            

            

            <!-- Submit button -->
            <input type="submit" class="btn btn-success btn-block mb-4" value="Save" name="operation">
            <input type="submit" class="btn btn-outline-success btn-block mb-4" value="Reset" name="operation">

          </sf:form>

        </div>
      </div>
    </div>
  </div>
</section>
</div>
<script>
	window.onload = function(){
		document.getElementById("one").onchange = function () {
			  document.getElementById("two").setAttribute("disabled", "disabled");
			  if (this.value == 'Paid')
			    document.getElementById("two").removeAttribute("disabled");
			};
	}
</script>