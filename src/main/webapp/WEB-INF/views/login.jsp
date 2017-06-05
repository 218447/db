<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
    <head>
        <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
        <meta charset="utf-8">
        <title>Login</title>
    </head>

    <body>
        <div>
            <form name="f" action="/forecast" method="post" accept-charset=utf-8>
                <fieldset>
                    <legend>Please Log In</legend>
                    <div if="${param.error}" class="alert alert-error">Invalid username and password.</div>

                    <label for="username"><p>Username <input type="text" id="username" name="username"/><p></label>
                    <label for="password"><p>Password <input type="password" id="password" name="password"/><p></label>
                    <div class="form1"><button type="submit" class="btn">Log In</button></div>
                </fieldset>
            </form>
        </div>

        <p class="register">Do you want to have an account?</p>

        <form action="/register" method="get"><div class="form2"><button type="submit" class="btn">Register</button></div></form>

    </body>
</html>