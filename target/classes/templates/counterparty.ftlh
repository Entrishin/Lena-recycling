<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container mt-5">
        <h3> Cписок контрагентов </h3>
        <!-- Таблица всех контейнеров-->
        <input class="form-control" id="myInput" type="text" placeholder="Поиск по таблице">
        <table
                class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Контрагент ID</th>
                <th>ИНН</th>
                <th>КПП</th>
                <th>Название</th>
            </tr>
            </thead>
            <tbody id="myTable">

            <#list counterparties as cp>
                <#if counterparty?has_content && cp.getId() == counterparty.getId()>
                    <tr>
                        <td>${cp.getId()}</td>
                        <td>${cp.getInn()}</td>
                        <td>${cp.getKpp()}</td>
                        <td>${cp.getName()}</td>
                    </tr>
                <#elseif !counterparty?has_content >
                    <tr>
                        <td>${cp.getId()}</td>
                        <td>${cp.getInn()}</td>
                        <td>${cp.getKpp()}</td>
                        <td>${cp.getName()}</td>
                    </tr>

                </#if>
            </#list>
            </tbody>
        </table>
    </div>



</@c.page>