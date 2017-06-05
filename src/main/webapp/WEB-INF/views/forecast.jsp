<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <head>
        <meta charset="utf-8">
        <link href="<c:url value="/resources/css/forecast.css" />" rel="stylesheet">
        <title>Forecast</title>
    </head>

    <body>

        <div>
            Prognoza pogody:
            Wprowadz rok na podstawie ktorego ma zostac wykonana prognoza i analiza.
        </div>
        <form name="f" action="/prognosis" method="post" accept-charset=utf-8>
            <select name="nazwa">
                <option>2016</option>
            </select>
            <label for="city"><p>Miasto <input type="text" id="city" name="city"/><p></label>
            <div class="form1"><input type="submit" class="btn" value="Send"></button></div>
         </form>

    </body>
</html>