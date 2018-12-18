package burger.data;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import burger.Burger;
import burger.Ingredient;

@Repository
public class JdbcBurgerRepository implements BurgerRepository{
	
	private JdbcTemplate jdbc;
	
	public JdbcBurgerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Burger save(Burger burger) {
		long burgerId = saveBurgerInfo(burger);
		burger.setId(burgerId);
		for (Ingredient ingredient : burger.getIngredients()) {
			saveIngredientToBurger(ingredient, burgerId);
		}
		return burger;
	}
	
	private long saveBurgerInfo(Burger burger) {
		burger.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"insert into Burger (name, createdAt) values (?, ?)",
			Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
				Arrays.asList(burger.getName(),new Timestamp(burger.getCreatedAt().getTime())));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}
	private void saveIngredientToBurger(Ingredient ingredient, long burgerId) {
		jdbc.update(
				"insert into Burger_Ingredients (burger, ingredient) " +
						"values (?, ?)",burgerId, ingredient.getId());
	}
}
