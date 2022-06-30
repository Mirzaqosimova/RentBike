<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 29.06.2022
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/loginc.css">
    <title>Title</title>
</head>
<body>

<section class="h-100 gradient-form" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">
                    <div class="row g-0">
                        <div class="col-lg-6">
                            <div class="card-body p-md-5 mx-md-4">

                                <div class="text-center">
                                    <img src="media/velik1.jpg"
                                         style="width: 185px;" alt="logo">
                                    <h4 class="mt-1 mb-5 pb-1">We are The Lotus Team</h4>
                                </div>

                                <form action="${pageContext.request.contextPath}/controller">
                                    <p>Please login to your account</p>
                                    <input type="hidden" name="command" value="login">
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="form2Example11"><h5>Username</h5></label>
                                        <input type="username" id="form2Example11" name="username" class="form-control"
                                               required />
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="form2Example22"><h5>Password</h5></label>
                                        <input type="password" id="form2Example22" name="password" class="form-control" required />
                                    </div>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <button  type="submit" class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3">Log in</button>
                                        <br>
                                    </div>

                                </form>
                                    <div class="d-flex align-items-center justify-content-center pb-4">
                                        <p class="mb-0 me-2">Don't have an account?</p>
                                   <a href="register.jsp"><button type="button" class="btn btn-outline-danger">Create new</button></a>

                                    </div>



                            </div>
                        </div>
                        <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                <h4 class="mb-4">We are more than just a company</h4>

                                <img src="media/fam1.jpg" alt="fam1" class="imgs">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
