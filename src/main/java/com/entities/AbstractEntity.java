package com.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

    @EmbeddedId
    protected EmbededId id;

    public AbstractEntity() {
        id = new EmbededId();
        id.setEnvId(0L);
    }

    public AbstractEntity(EmbededId id) {
        this.id = id;
    }

    public EmbededId getId() {
        return id;
    }

    public void setId(EmbededId id) {
        this.id = id;
    }

    public void setTechnicalId(int technicalId) {
        if (id == null) {
            id = new EmbededId();
        }
        id.setId(technicalId);
    }

    public void setEnvId(int envId) {
        if (id == null) {
            id = new EmbededId();
        }
        id.setEnvId(envId);
    }
}
