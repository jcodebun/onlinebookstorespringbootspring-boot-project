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

          <sf:form method="post" action="${pageContext.request.contextPath}/home/login" modelAttribute="form"><br/>
           <h2>Login</h2><hr/>
            <b><%@ include file="businessMessage.jsp"%></b>
            <!-- Email input -->
             <div class="form-outline mb-4 ">
            <s:bind path="login">
						<label for="inputEmail4" class="form-label">Email</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Email" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
              
            </div>

            <!-- Password input -->
            <div class="form-outline mb-4">
             <s:bind path="password">
						<label for="inputEmail4" class="form-label">Password</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>

            <!-- 2 column grid layout for inline styling -->
            <div class="row mb-4">
              <div class="col d-flex justify-content-center">
              </div>

            </div>

            <!-- Submit button -->
            <input type="submit" class="btn btn-success btn-block mb-4" value="Login" name="operation">
            <input type="submit" class="btn btn-outline-success btn-block mb-4" value="Reset" name="operation">

          </sf:form>

        </div>
      </div>
    </div>
  </div>
</section>
</div>
