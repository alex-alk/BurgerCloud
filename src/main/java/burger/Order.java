package burger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="Burger_Order")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private Date placedAt;
	
	@NotBlank(message="Numele este obligatoriu")
	private String deliveryName;
	
	@NotBlank(message="Strada este obligatorie")
	private String deliveryStreet;
	
	@NotBlank(message="Orașul este obligatoriu")
	private String deliveryCity;
	
	@NotBlank(message="Țara este obligatorie")
	private String deliveryState;
	
	@NotBlank(message="Codul poștal este obligatoriu")
	private String deliveryZip;
	
	@CreditCardNumber(message="Numarul de card nu este valid")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Trebuie să aibe formatul MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="CVV invalid")
	private String ccCVV;
	
	@ManyToMany(targetEntity=Burger.class)
	private List<Burger> burgers = new ArrayList<>();
	
	public void addBurger(Burger burger) {
	    this.burgers.add(burger);
	}
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
}
