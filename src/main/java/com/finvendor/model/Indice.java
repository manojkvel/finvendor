package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "indice")
public class Indice implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "index_id")
    private String indexId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "family")
    private String family;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "index_details_id")
    private IndiceDetails indiceDetails;

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public IndiceDetails getIndiceDetails() {
        return indiceDetails;
    }

    public void setIndiceDetails(IndiceDetails indiceDetails) {
        this.indiceDetails = indiceDetails;
    }
}
