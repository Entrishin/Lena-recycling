<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container mt-5">
        <h3> Cписок водителей </h3>
        <!-- Таблица всех контейнеров-->
        <input class="form-control" id="myInput" type="text" placeholder="Поиск по таблице">
        <table
                class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Водитель</th>
                <th>Контрагент</th>
                <th>Контейнер</th>
                <th>Адрес контейнера</th>
                <th>Адрес склада</th>
                <th>В пути</th>
                <th>Погрузка</th>
                <th>В пути</th>
                <th>Выгрузка</th>
            </tr>
            </thead>
            <tbody id="myTable">

            <#list drivers as dr>
                <tr>
                    <td>${dr.getId()}</td>
                    <td>
                        <a href="/counterparty/${dr.getCounterparty().getName()}">${dr.getCounterparty().getName()}</a>
                    </td>
                    <#if dr.getContainer_id()??>
                        <td>${dr.getContainer_id().getId()}</td>
                        <td>${dr.getContainer_id().getAddress()}</td>
                    <#else>
                        <td></td>
                        <td></td>
                    </#if>
                    <td>${dr.getAdressTo()}</td>

                    <td>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated"
                                 role="progressbar" aria-valuenow=${dr.getExecution_waytocontainer()} aria-valuemin="0"
                                 aria-valuemax="100" style="width: ${dr.getExecution_waytocontainer()}%" >${dr.getExecution_waytocontainer()}%</div>
                        </div>
                    </td>

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