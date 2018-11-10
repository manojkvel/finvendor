package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="index_comp_details")
public class IndexCompDetails implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name="index_id")
    private String indexId;

    @Column(name="company_id")
    private String companyId;

    @Column(name="timestamp")
    private String timestamp;

    public void setId(String id) {
        this.id = id;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
