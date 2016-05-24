package model;

import javax.persistence.Access;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@Access(FIELD)
class BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    public BaseEntity() {
    }

    BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
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
        return (id == null) ? 0 : id;
    }
}
