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

          <sf:form method="post" action="${pageContext.request.contextPath}/home/pay" modelAttribute="form"><br/>
            <h2>Payment Page</h2><hr/>
            <b><%@ include file="businessMessage.jsp"%></b>
            
            
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
