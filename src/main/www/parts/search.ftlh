<#macro list>
    <input type="button" value="на главную" onClick='location.href="/main"'>
    <h3>Список духов</h3>

    <#list perfumes as perfume>
<#--        <p>${perfumes[2].perfumer}</p>-->
        <div>
            <#if perfume.filename??>
                <img src="/img/${perfume.filename}">
            </#if>
        </div>
    <table>
        <tr>
            <th align="left">Характеристика</th>
            <th align="left">Описание</th>
        </tr>
        <tr><td>id:</td><td>${perfume.id}</td></tr>
        <tr><td>Название парфюма:</td><td>${perfume.perfumeTitle}</td></tr>
        <tr><td>Парфюмер:</td><td>${perfume.perfumer}</td></tr>
        <tr><td>Год выпуска:</td><td>${perfume.year}</td></tr>
        <tr><td>Страна производитель: </td><td>${perfume.country}</td></tr>
        <tr><td>Пол: </td><td>${perfume.perfumeGender}</td></tr>
        <tr><td>Верхние ноты: </td><td>${perfume.fragranceTopNotes}</td></tr>
        <tr><td>Средние ноты: </td><td>${perfume.fragranceMiddleNotes}</td></tr>
        <tr><td>Базовые ноты: </td><td>${perfume.fragranceBaseNotes}</td></tr>
        <tr><td>Описание: </td><td>${perfume.description}</td></tr>
    </table>
    </#list>
</#macro>