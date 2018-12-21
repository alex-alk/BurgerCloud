package burger.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import burger.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
	//Jdbc version:
	/*Iterable<Ingredient> findAll();
	 *Ingredient findById(String id);
	 *Ingredient save(Ingredient ingredient);
	 */
}
