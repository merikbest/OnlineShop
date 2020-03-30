<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <input type="button" value="на главную" onClick='location.href="/main"'>
        <form method="post" action="/add" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <p><b>Заполните фоорму</b></p>
            <p>Название парфюма:</p>
            <input type="text" name="perfumeTitle" size="40" placeholder="Название парфюма">
            <p>Парфюмер: </p>
            <input type="text" name="perfumer" size="40">
            <p>Год выпуска: </p>
            <input type="number" name="year" size="40">
            <p>Страна производитель: </p>
            <input type="text" name="country" size="40">
            <p>Пол: </p>
            <input type="text" name="perfumeGender" size="40">
            <p>Верхние ноты: </p>
            <input type="text" name="fragranceTopNotes" size="40">
            <p>Средние ноты: </p>
            <input type="text" name="fragranceMiddleNotes" size="40">
            <p>Базовые ноты: </p>
            <input type="text" name="fragranceBaseNotes" size="40">
            <p>Описание: </p>
            <textarea name="description" cols="40" rows="3"></textarea>
            <p>
                <button type="submit">Добавить</button>
                <input type="file" name="file">
            </p>
        </form>
    </div>
</@c.page>