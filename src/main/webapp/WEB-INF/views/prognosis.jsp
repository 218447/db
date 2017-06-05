<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
    <head>
         <meta charset="utf-8">
         <link href="<c:url value="/resources/css/prognosis.css" />" rel="stylesheet">
         <meta charset="utf-8">
    </head>

    <body>
        <div class="text">Dane przeanalizowane na podstawie 2016 roku i prognoza na przyszlosc</div>

        <p>Dane obliczone dla ${city}:</p>
        <div>Srednia temperatura dla roku 2016: ${mean}</div>
        <div> Trend dla przyszlego roku:
                <p>Styczen: "${months[0]}"</p>
                <p>Luty: "${months[1]}"</p>
                <p>Marzec: "${months[2]}"</p>
                <p>Kwiecien: "${months[3]}"</p>
                <p>Maj: "${months[4]}"</p>
                <p>Czerwiec: "${months[5]}"</p>
                <p>Lipec: "${months[6]}"</p>
                <p>Sierpien: "${months[7]}"</p>
                <p>Wrzesien: "${months[8]}"</p>
                <p>Pazdziernik: "${months[9]}"</p>
                <p>Listopad: "${months[10]}"</p>
                <p>Grudzien: "${months[11]}"</p>
         </div>
    </body>

</html>