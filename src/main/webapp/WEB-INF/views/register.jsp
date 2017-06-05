<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

    <head>
        <link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">
        <meta charset="utf-8">
        <title>Register</title>
    </head>

    <body>
        <div>
            <form name="f" action="/registering" method="post">
                <fieldset>
                    <legend>Please Register</legend>
                    <div if="${param.error}" class="alert alert-error">Invalid username and password.</div>

                    <label for="username"><p>Username <input type="text" id="username" name="username"/><p></label>
                    <label for="password"><p>Password <input type="password" id="password" name="password"/><p></label>
                    <div class="form1"><button type="submit" class="btn">Register</button></div>
                </fieldset>
            </form>
        </div>

        <p class="register">Do you have an account?</p>

        <form action="/login" method="get"><div class="form2"><button type="submit" class="btn">Log in</button></div></form>

    </body>
</html>