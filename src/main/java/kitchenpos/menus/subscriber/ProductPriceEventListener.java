package kitchenpos.menus.subscriber;

import kitchenpos.menus.application.MenuService;
import kitchenpos.products.publisher.ProductPriceChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProductPriceEventListener {

    private final MenuService menuService;

    public ProductPriceEventListener(MenuService menuService) {
        this.menuService = menuService;
    }

    @TransactionalEventListener
    public void listen(ProductPriceChangedEvent event) {
        menuService.checkHideAndPrice(event.getProductId());
    }

}
