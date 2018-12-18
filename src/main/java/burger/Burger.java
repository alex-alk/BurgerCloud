package burger;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Burger {
	
	private long id;
	private Date createdAt;
	
	@Size(min=2, message="Denumirea trebuie să conțină cel puțin 2 caractere")
	private String name;
	
	@NotNull(message="Trebuie să alegeți cel puțin un ingredient")
	@Size(min=1, message="Trebuie să alegeți cel puțin un ingredient")
	private List<Ingredient> ingredients;
}
