<#import "parts/common.ftlh" as c>
<#import "parts/login_registration.ftlh" as l>

<@c.page>
    Add new user
    ${message ! ""}
    <#--${message?ifExists} ИСПРАВЛЕНИЯ ПАДЕНИЯ ФОРМЫ РЕГИСТРАЦИИ КОГДА НЕТУ СООБШЕНИЙ ОБ ОШИБКАХ  -->
    <@l.login "/registration"/>
</@c.page>