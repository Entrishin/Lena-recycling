


<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container mt-5">
        <h3> Cписок контейнеров </h3>
        <!-- Таблица водителей-->
        <table
                class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Номер контейнера</th>
                <th>Тип контейнера</th>
                <th>Адрес</th>
                <th>Заполненность</th>
                <th>Статус</th>
            </tr>
            </thead>
            <tbody>

            <#list containers as tr>
                <tr>
                    <td>${tr.getId()}</td>
                    <td>${tr.getContainerType()}</td>
                    <td>${tr.getAddress()}</td>
                    <td>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated <#if tr.getFullness() < 60 > bg-success <#elseif tr.getFullness() < 90 > bg-warning <#else> bg-danger </#if> "
                                 role="progressbar" aria-valuenow=${tr.getFullness()} aria-valuemin="0"
                                 aria-valuemax="100" style="width: ${tr.getFullness()}%" >${tr.getFullness()}%</div>
                        </div>

                    </td>
                    <td>${tr.getStatus()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>




