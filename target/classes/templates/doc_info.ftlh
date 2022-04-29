<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/test_doc" method="post">

        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="text-align: center" colspan="4" scope="col">Накладная № ${billNumber}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td style="font-weight: bold" >Грузоотправитель</td>
                <td>${counterPartyDriverName}</td>
                <td>${counterPartyDriverInn}</td>
                <td>Blockchain???</td>
            </tr>
            <tr>
                <td  style="font-weight: bold">Грузополучатель</td>
                <td>${counterPartyStorageName}</td>
                <td>${counterPartyStorageInn}</td>
                <td>Blockchain???</td>
            </tr>
            <tr>
                <td  style="font-weight: bold ; text-align: center" colspan="4">Погрузо-разгрузочные операции</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1">Номер операции</td>
                <td colspan="2">${operationNumber}</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Водитель</td>
                <td colspan="3">${driverName}</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1">Автомобиль</td>
                <td colspan="3">${autoNum}</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Пункт погрузки</td>
                <td colspan="3">${driverLoading}</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Пункт выгрузки</td>
                <td colspan="3">${driverUnloading}</td>
            </tr>
            <tr>

                <td colspan="4"></td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Наименование груза</td>
                <td colspan="3">${containerType}</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Номер контейнера</td>
                <td colspan="3" >${containerId}</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Вес</td>
                <td colspan="3" >${weight}кг</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Время погрузки</td>
                <td colspan="3">${timeLoading}мин</td>
            </tr>
            <tr>
                <td  style="font-weight: bold" colspan="1" >Время выгрузки</td>
                <td colspan="3" >${timeUnloading}мин</td>
            </tr>
            </tbody>
        </table>


        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>
            <button type="submit" class="btn btn-primary" >Что-то сделать...</button>
        </div>
    </form>
</@c.page>