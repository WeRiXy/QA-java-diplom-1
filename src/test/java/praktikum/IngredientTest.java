package praktikum;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0} - {1}, {2}")
    public static Object[][] getData() {
        return new Object[][] {
                { IngredientType.SAUCE, new Faker().food().ingredient(),(float) new Faker().number().randomDouble(2,1,100000) },
                { IngredientType.FILLING,"", 0 },
                { IngredientType.SAUCE,new Faker().food().ingredient(), Float.MIN_EXPONENT},
        };
    }

    @Before
    public void init(){
        ingredient = new Ingredient(type,name,price);
    }

    @Test
    public void getPrice() {
        assertEquals(price,ingredient.getPrice(),0);
    }

    @Test
    public void getName() {
        assertEquals(name,ingredient.getName());
    }

    @Test
    public void getType() {
        assertEquals(type,ingredient.getType());
    }
}