<#include "security.ftlh">
<#import "login.ftlh" as l>

<link rel="stylesheet" href="/static/style.css">
<nav class="test navbar navbar-expand-lg navbar-light bg-light test">
    <a class="navbar-brand" href="/">
        <img src="https://makulatura.group/wp-content/uploads/elementor/thumbs/recycling-ouu0z270pvol91myrcop9avucoh1n64ejfvrv09lbw.png"  width="30" height="30" class="d-inline-block align-top" alt="">
        Рециклинг
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="navbar-item mt-1">
                <a class="nav-link" href="/containers">Контейнеры</a>
            </li>
            <li class="navbar-item mt-1">
                <a class="nav-link" href="/drivers">Водители</a>
            </li>
            <#if isAdmin>
            <li class="nav-item mt-1">
                <a class="nav-link" href="/user">Список пользователей</a>
            </li>
            </#if>
            <#if user??>
                <li class="nav-item mt-1">
                    <a class="nav-link" href="/user/profile">Профиль</a>
                </li>
            </#if>
        </ul>
        <#if user??>
            <div class="navbar-text mr-3">${name}</div>
            <@l.logout/>
        </#if>
    </div>
</nav>