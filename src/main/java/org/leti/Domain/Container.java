package org.leti.Domain;


import javax.persistence.*;
import javax.persistence.Id;

//контейнер
@Entity
@Table(name = "container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //тип контейнра стекло/пластик/бумага
    private String containerType;
    //степень наполненности контейнера 1-100
    private int fullness;
    //статус - заполняется/заполнен (случаи поломки и т.д. не делал)
    private String status;
    //адрес контейнера
    private String address;
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

    public Double popularity; //"популярность" контейнера - характеристика, определяющая, как быстро наполняется контейнер 0 - медленно, 1 - быстро

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
