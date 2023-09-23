package kitchenpos.orders.eatinorders.fixture;

import kitchenpos.common.domain.Price;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.orders.eatinorders.domain.*;
import kitchenpos.orders.ordertables.domain.OrderTable;

import java.util.Arrays;

import static kitchenpos.menus.application.fixtures.MenuFixture.menu;

public class EatInOrderFixture {

    private EatInOrderFixture() {

    }

    public static EatInOrder order(final EatInOrderStatus status, final OrderTable orderTable) {
        return new EatInOrder(
                status,
                orderLineItems(orderLineItem()),
                new OrderTableId(orderTable.getIdValue())
        );
    }

    public static EatInOrder order(final EatInOrderStatus status, final OrderTable orderTable, EatInOrderLineItem... orderLineItems) {
        return new EatInOrder(
                status,
                orderLineItems(orderLineItems),
                new OrderTableId(orderTable.getIdValue())
        );
    }

    public static EatInOrder order(final OrderTable orderTable, EatInOrderLineItem... orderLineItems) {
        return new EatInOrder(
                orderLineItems(orderLineItems),
                new OrderTableId(orderTable.getIdValue())
        );
    }

    public static EatInOrderLineItem orderLineItem() {
        Menu menu = menu();
        return orderLineItem(
                menu,
                1L,
                menu.getPriceValue().longValue()
        );
    }

    public static EatInOrderLineItem orderLineItem(Menu menu, long quantity, long price) {
        return new EatInOrderLineItem(
                quantity,
                new Price(price),
                new OrderedMenu(menu.getIdValue(), menu.getNameValue(), menu.getPrice())
        );
    }

    public static EatInOrderLineItems orderLineItems(EatInOrderLineItem... orderLineItems) {
        return new EatInOrderLineItems(Arrays.asList(orderLineItems));
    }

}
