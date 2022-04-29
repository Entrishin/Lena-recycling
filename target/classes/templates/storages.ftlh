<#import "parts/common.ftlh" as c>


<@c.page>

    <div class="container mt-5">
        <h3> Cписок складов </h3>
        <!-- Таблица складов (уникальная)-->
        <table
                class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Адрес</th>
                <th>Контрагент</th>
            </tr>
            </thead>
            <tbody id="myTable">

            <#list uniqueStorages as unSt>
                <tr>
                    <td>${unSt.getAddress()}</td>
                    <td>${unSt.getCounterparty().getName()}</td>
                </tr>
            </#list>
            </tbody>
        </table>


        <h3> Переработка контейнеров </h3>
        <!-- Таблица обработки контейнеров складами-->
        <input class="form-control" id="myInput" type="text" placeholder="Поиск по таблице">
        <table
                class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Адрес</th>
                <th>Контрагент</th>
                <th>Контейнер</th>
                <th>Переработка</th>
            </tr>
            </thead>
            <tbody id="myTable">

            <#list storages as st>
                <tr>

                    <td>
                        <a href="/doc_info/${st.getId()}">${st.getId()}</a>
                    </td>

                    <td>${st.getAddress()}</td>
                    <td>${st.getCounterparty().getName()}</td>

                    <#if st.getContainer_id()??>
                        <td>${st.getContainer_id().getId()}</td>
                    <#else>
                        <td></td>
                    </#if>

                    <td>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated"
                                 role="progressbar" aria-valuenow=${st.getExecution_recycling()} aria-valuemin="0"
                                 aria-valuemax="100" style="width: ${st.getExecution_recycling()}%" >${st.getExecution_recycling()}%</div>
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>