package kitchenpos.products.tobe.domain;

import kitchenpos.common.domain.ProfanityPolicy;
import kitchenpos.products.exception.ProductDisplayedNameException;
import kitchenpos.products.exception.ProductErrorCode;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProductDisplayedName {
    private String name;

    protected ProductDisplayedName() {

    }

    public ProductDisplayedName(String name, ProfanityPolicy profanityPolicy) {
        validate(name, profanityPolicy);
        this.name = name;
    }

    private void validate(String text, ProfanityPolicy profanityPolicy) {
        if (isNullAndEmpty(text)) {
            throw new ProductDisplayedNameException(ProductErrorCode.NAME_IS_NULL_OR_EMPTY);
        }

        if (profanityPolicy.containsProfanity(text)) {
            throw new ProductDisplayedNameException(ProductErrorCode.NAME_HAS_PROFANITY);
        }
    }

    private boolean isNullAndEmpty(String name) {
        return name == null || name.isBlank();
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDisplayedName that = (ProductDisplayedName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
