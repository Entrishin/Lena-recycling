<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container mt-5">
        <h3> Cписок всех контейнеров </h3>
        <!-- Таблица всех контейнеров-->
        <table
                class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Номер водителя</th>
                <th>Номер контейнера</th>
                <th>Адрес контейнера</th>
                <th>Адрес склада</th>
                <th>Погрузка</th>
                <th>В пути</th>
                <th>Выгрузка</th>
            </tr>
            </thead>
            <tbody>

            <#list drivers as dr>
                <tr>
                    <td>${dr.getId()}</td>
                    <td>${dr.getContainer_id().getId()}</td>
                    <td>${dr.getContainer_id().getAddress()}</td>
                    <td>${dr.getAdressTo()}</td>

                    <td>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated"
                                 role="progressbar" aria-valuenow=${dr.getExecution_loading()} aria-valuemin="0"
                                 aria-valuemax="100" style="width: ${dr.getExecution_loading()}%" >${dr.getExecution_loading()}%</div>
                        </div>
                    </td>

                    <td>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated"
                                 role="progressbar" aria-valuenow=${dr.getExecution_ontheway()} aria-valuemin="0"
                                 aria-valuemax="100" style="width: ${dr.getExecution_ontheway()}%" >${dr.getExecution_ontheway()}%</div>
                        </div>
                    </td>

                    <td>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated"
                                 role="progressbar" aria-valuenow=${dr.getExecution_unloading()} aria-valuemin="0"
                                 aria-valuemax="100" style="width: ${dr.getExecution_unloading()}%" >${dr.getExecution_unloading()}%</div>
                        </div>
                    </td>

                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>