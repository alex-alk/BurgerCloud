package burger.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import burger.Burger;
import burger.Ingredient;
import burger.Ingredient.Type;
import burger.Order;
import burger.data.BurgerRepository;
import burger.data.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignBurgerController {

	private final IngredientRepository ingredientRepo;
	private BurgerRepository burgerRepo;
	
	@Autowired
	public DesignBurgerController(IngredientRepository ingredientRepo,
								  BurgerRepository burgerRepo) {
		this.ingredientRepo = ingredientRepo;
		this.burgerRepo = burgerRepo; 
	}
	
	@ModelAttribute(name="order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name="burger")
	public Burger burger() {
		return new Burger();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i->ingredients.add(i));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
		return "design";			
	}
	@PostMapping
	public String processDesign(@Valid Burger burger, Errors errors, 
			@ModelAttribute Order order, Model model) {
		
		if(errors.hasErrors()) {
			List<Ingredient> ingredients = new ArrayList<>();
			ingredientRepo.findAll().forEach(i->ingredients.add(i));
			Type[] types = Ingredient.Type.values();
			for (Type type : types) {
				model.addAttribute(type.toString().toLowerCase(),
						filterByType(ingredients, type));
			}
			return "design";
		}
		Burger saved = burgerRepo.save(burger);
		order.addBurger(saved);
		return "redirect:/comezi/curent";
	}
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		    return ingredients
		              .stream()
		              .filter(x -> x.getType().equals(type))
		              .collect(Collectors.toList());
	}
}
