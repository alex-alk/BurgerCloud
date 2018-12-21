package burger.data;

import org.springframework.data.repository.CrudRepository;

import burger.Burger;

public interface BurgerRepository extends CrudRepository<Burger, Long>{
	
	//Jdbc version:
	//Burger save(Burger burger);
}
