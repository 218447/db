<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>

    <head>
         <link href="<c:url value="/resources/css/error.css" />" rel="stylesheet">
    </head>

    <body>
        <div class="errorblock">User with such username already exists</div>

        <p>Return to registering page</p>
        <form action="/register" method="get">
            <div class="form-actions">
                <button type="submit" class="btn">login>
            </div>

        </form>
    </body>

</html>