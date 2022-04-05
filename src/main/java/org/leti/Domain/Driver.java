package org.leti.Domain;

import javax.persistence.*;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String status;
    private double execution_waytocontainer;
    private double execution_loading;
    private double execution_ontheway;
    private double execution_unloading;
    private String adressTo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "container_id")
    private Container container_id;

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
