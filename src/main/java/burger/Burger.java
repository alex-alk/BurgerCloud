package burger;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Burger {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ids")
	private long id;
	
	@Column(name="createdAt")
	private Date createdAt;
	
	@Size(min=2, message="Denumirea trebuie să conțină cel puțin 2 caractere")
	private String name;
	
	@ManyToMany(targetEntity=Ingredient.class)
	@NotNull(message="Trebuie să alegeți cel puțin un ingredient")
	@Size(min=1, message="Trebuie să alegeți cel puțin un ingredient")
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
