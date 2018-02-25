package com.entities;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class EmbededId implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "env_id")
    private long envId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEnvId() {
        return envId;
    }

    public void setEnvId(long envId) {
        this.envId = envId;
    }

    @Override
    public String toString() {
        return "EmbededId{" +
                "id=" + id +
                ", envId=" + envId +
                '}';
    }
}
