<#import "parts/common.ftlh" as c>

<@c.page>
<h1>Имитационное моделирования инфраструктуры рециклинга</h1>

    <form action="/fillcontainers" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" > Количество контейнеров/фандоматов  </label>
            <div class="col-sm-6">
                <input type="number" name="numOfContainers" class = "form-control" placeholder="10" required/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Количество водителей </label>
            <div class="col-sm-6">
                <input type="text" name="numOfDrivers" class = "form-control" placeholder="5" required/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>
            <button type="submit" class="btn btn-primary" >Запустить</button>
        </div>
    </form>








</@c.page>