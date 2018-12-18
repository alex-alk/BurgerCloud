package burger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
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
	
	private List<Burger> burgers = new ArrayList<>();
	
	public void addBurger(Burger burger) {
	    this.burgers.add(burger);
	  }
}
