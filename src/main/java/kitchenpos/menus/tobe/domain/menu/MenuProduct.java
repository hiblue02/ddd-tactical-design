package kitchenpos.menus.tobe.domain.menu;

import kitchenpos.common.domain.Price;
import kitchenpos.menus.exception.MenuErrorCode;
import kitchenpos.menus.exception.MenuProductException;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Table(name = "menu_product")
@Entity
public class MenuProduct {

    @EmbeddedId
    private MenuProductId id;

    @Embedded
    private ProductId productId;

    @Transient
    private Price productPrice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Embedded
    private MenuProductQuantity quantity;

    protected MenuProduct() {

    }

    public MenuProduct(ProductId productId, Price productPrice, MenuProductQuantity quantity) {
        validateProductPrice(productPrice);
        this.productPrice = productPrice;
        this.productId = productId;
        this.quantity = quantity;
    }

    public MenuProduct(ProductId productId, Price productPrice, long quantity) {
        this(productId, productPrice, new MenuProductQuantity(quantity));
    }

    public MenuProduct(UUID productId, Price productPrice, long quantity) {
        this(new ProductId(productId), productPrice, new MenuProductQuantity(quantity));
    }


    private void validateProductPrice(Price productPrice) {
        if (productPrice == null) {
            throw new MenuProductException(MenuErrorCode.MENU_PRODUCT_PRICE_IS_EMPTY);
        }
    }

    public Price calculatePrice() {
        return productPrice.multiply(quantity.getValue());
    }

    public void mapMenu(Menu menu) {
        this.menu = menu;
    }

    public void fetchPrice(Price price) {
        this.productPrice = price;
    }

    public MenuProductId getId() {
        return id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public UUID getProductIdValue() {
        return productId.getValue();
    }


    public boolean hasProduct(ProductId productId) {
        return this.productId.equals(productId);
    }

    public long getQuantityValue() {
        return quantity.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuProduct that = (MenuProduct) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(productId, that.productId)) return false;
        return Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }

}
