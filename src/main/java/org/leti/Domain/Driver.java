package org.leti.Domain;

import javax.persistence.*;


//водитель
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //имя
    private String name;
    //Номер машины
    private String autoNum;
    //статус - в работе "Working" или ожидает наполнения контейнера "Ok"
    private String status;
    // execution - Означает, что это выполнение чего-то, т.е. на сайте отображается в виде полоски процентов от 1 до 100
    //waytocontainer - в пути до контейнера
    //loading - погрузка
    //ontheway (на схеме я назвал это waytostorage) - в пути до склада
    //unloading - разгрузка
    private double execution_waytocontainer;
    private double execution_loading;
    private double execution_ontheway;
    private double execution_unloading;
    // адресс контейнера (не используется, т.к. берется из таблицы контейнеров по внешнему ключу container_id
    private String adressTo;

    //внешний ключ к контейнеру
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "container_id")
    private Container container_id;

    //внешний ключ к контрагенту
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getExecution_waytocontainer() {
        return execution_waytocontainer;
    }

    public void setExecution_waytocontainer(double execution_waytocontainer) {
        this.execution_waytocontainer = execution_waytocontainer;
    }

    public String getAdressTo() {
        return adressTo;
    }

    public void setAdressTo(String adressTo) {
        this.adressTo = adressTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutoNum() {
        return autoNum;
    }

    public void setAutoNum(String autoNum) {
        this.autoNum = autoNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getExecution_loading() {
        return execution_loading;
    }

    public void setExecution_loading(double execution_loading) {
        this.execution_loading = execution_loading;
    }

    public double getExecution_ontheway() {
        return execution_ontheway;
    }

    public void setExecution_ontheway(double execution_ontheway) {
        this.execution_ontheway = execution_ontheway;
    }

    public double getExecution_unloading() {
        return execution_unloading;
    }

    public void setExecution_unloading(double execution_unloading) {
        this.execution_unloading = execution_unloading;
    }

    public Container getContainer_id() {
        return container_id;
    }

    public void setContainer_id(Container container_id) {
        this.container_id = container_id;
    }
}
