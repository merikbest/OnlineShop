<#include "security.ftlh">
<#import "login_registration.ftlh" as l>

<div>
    <nav class="navbar navbar-expand-lg navbar-dark " style="background-color: black">
        <div class="container">
            <a class="navbar-brand" href="#">ADMIRE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav justify-content-center">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Главная</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Для женщин</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Для мужчин</a>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Бренды
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Еще</a>
                        </div>
                    </li>
                    <#if isAdmin>
                        <li class="nav-item dropdown active">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                ADMIN
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/list">Список всех товаров</a>
                                <a class="dropdown-item" href="/user">Список пользователей</a>
                                <a class="dropdown-item" href="/add">Добавить товар</a>
                            </div>
                        </li>
                    </#if>
                </ul>
                <ul class="nav navbar-nav ml-auto justify-content-end">
                    <li class="nav-item active">
                        <a class="nav-link" href="/login">Корзина</a>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Личный кабинет
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/login">Авторизация</a>
                            <a class="dropdown-item" href="/registration">Регистрация</a>
                        </div>
                    </li>
                </ul>
                <div class="navbar-text mr-3">${name}</div>
                <@l.logout/>
            </div>
        </div>
    </nav>
</div>


