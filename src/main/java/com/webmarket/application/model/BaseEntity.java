package com.webmarket.application.model;

import org.springframework.data.annotation.Id;

class BaseEntity {
    @Id
    private String id;

    public BaseEntity() {
    }

    BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseEntity that = (BaseEntity) o;
        return !(id == null || that.id == null) && id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : Integer.valueOf(id);
    }
}
