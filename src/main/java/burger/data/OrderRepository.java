package burger.data;

import org.springframework.data.repository.CrudRepository;

import burger.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	//Jdbc version:
	//Order save(Order order);
}
