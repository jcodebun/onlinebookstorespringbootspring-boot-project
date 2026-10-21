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

          <sf:form method="post" action="${pageContext.request.contextPath}/home/newUser" modelAttribute="form"><br/>
            <h2>Register & Download</h2><hr/>
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
            
            <!-- Firstname  -->
            <div class="form-outline mb-4">
             <s:bind path="firstName">
						<label for="inputEmail4" class="form-label">First Name</label>
						<sf:input type="text" path="${status.expression}"
							placeholder="Enter First Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            
            <!-- Lastname -->
            <div class="form-outline mb-4">
             <s:bind path="lastName">
						<label for="inputEmail4" class="form-label">Last Name</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Last Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            
            <!-- Mobile No -->
            <div class="form-outline mb-4">
             <s:bind path="mobileNo">
						<label for="inputEmail4" class="form-label">Mobile No.</label>
						<sf:input type="text" path="${status.expression}"
							placeholder="Enter Mobile No." class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            
            <!-- Card Number -->
            <div class="form-outline mb-4">
             <s:bind path="cardNumber">
						<label for="inputEmail4" class="form-label">Card No.</label>
						<sf:input type="text" path="${status.expression}"
							placeholder="Enter Card No." class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            <!-- Expiration -->
            <div class="form-outline mb-4">
             <s:bind path="expiration">
						<label for="inputEmail4" class="form-label">Expiration</label>
						<sf:select id="one" class="form-control" path="${status.expression}">
									<sf:option value="" label="---Month---" />
									<sf:options   items="${mm}" />
								</sf:select> <br>
								<sf:select id="one" class="form-control" path="${status.expression}">
									<sf:option value="" label="---Year---" />
									<sf:options   items="${yy}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
            <!-- CVV -->
			<div class="form-outline mb-4">
             <s:bind path="cvv">
						<label for="inputEmail4" class="form-label">CVV</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter CVV" class="form-control" />
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
