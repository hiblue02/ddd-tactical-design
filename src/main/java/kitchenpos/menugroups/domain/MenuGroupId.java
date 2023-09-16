package kitchenpos.menugroups.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MenuGroupId implements Serializable {

    @Column(name = "id", columnDefinition = "binary(16)")
    private UUID id;

    public MenuGroupId(UUID menuGroupId) {
        this.id = menuGroupId;
    }

    public MenuGroupId() {
        this.id = UUID.randomUUID();
    }

    public UUID getValue() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuGroupId menuId = (MenuGroupId) o;

        return Objects.equals(id, menuId.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
