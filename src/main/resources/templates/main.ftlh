<#import "parts/common.ftlh" as c>
<#import "parts/login_registration.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post" action="filter" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="filter">
            <button type="submit">Найти</button>
            <input type="button" value="Показать список товаров" onClick='location.href="/list"'>
            <input type="button" value="Добвить товары" onClick='location.href="/add"'>
        </form>
    </div>
</@c.page>