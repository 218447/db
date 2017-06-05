<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <head>
        <meta charset="utf-8">
        <link href="<c:url value="/resources/css/forecast.css" />" rel="stylesheet">
        <title>Admin</title>
    </head>

    <body>

        <div>
            Strona administratora:
            Tutaj dodawaj dane dla kolejnych lat
        </div>
        <form id = "uploadbanner" method = "post" action = "#">
              <input id = "fileupload" type = "file" />
              <input type = "submit" value = "submit" id = "submit" />
        </form>
    </body>
</html>