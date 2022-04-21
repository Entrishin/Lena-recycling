package org.leti.Domain;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    private double execution_recycling;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "container_id")
    private Container container_id;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Container getContainer_id() {
        return container_id;
    }

    public void setContainer_id(Container container_id) {
        this.container_id = container_id;
    }

    public double getExecution_recycling() {
        return execution_recycling;
    }

    public void setExecution_recycling(double execution_recycling) {
        this.execution_recycling = execution_recycling;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }
}
