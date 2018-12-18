package burger.data;

import burger.Order;

public interface OrderRepository {
	Order save(Order order);
}
