<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">

    <head>
        <link href="<c:url value="/resources/css/forecast.css" />" rel="stylesheet">

        <title tiles:fragment="title">Forecast</title>
    </head>

    <body>
        <div tiles:fragment="content">
            Prognoza pogody:
            Wpisz liczbe dni na ktorych podstawie ma byc wykonana prognoza oraz dni na jak dlugo ma ona wynosić:
        </div>
        <form name="f" th:action="@{/prognosis}" method="post" accept-charset=utf-8>
                        <fieldset>
                            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                            <label for="dni przeszłe"><p>Username <input type="text" id="past" name="past"/><p></label>
                            <label for="dni naprzód"><p>Password <input type="password" id="future" name="future"/><p></label>
                            <div class="form1"><button type="submit" class="btn">Send</button></div>
                        </fieldset>
         </form>

    </body>
</html>