    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">

    <head>
        <link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">

        <title>Register</title>
    </head>

    <body>
        <div tiles:fragment="content">
            <form name="f" th:action="@{/registering}" method="post">
                <fieldset>
                    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                    <legend>Please Register</legend>
                    <div th:if="${param.error}" class="alert alert-error">Invalid username and password.</div>

                    <label for="username"><p>Username <input type="text" id="username" name="username"/><p></label>
                    <label for="password"><p>Password <input type="password" id="password" name="password"/><p></label>
                    <div class="form1"><button type="submit" class="btn">Register</button></div>
                </fieldset>
            </form>
        </div>

        <p class="register">Do you have an account?</p>

        <form action="@{/login}" method="get"><div class="form2"><button type="submit" class="btn">Log in</button></div></form>

    </body>
</html>