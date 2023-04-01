package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private static final float DELTA_ASSERT = 0.01f;
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient ingredient2;

    @Test
    public void setBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        Assert.assertNotNull(burger.bun);
    }

    @Test
    public void addIngredientOne() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        Assert.assertEquals(1,burger.ingredients.size());
    }

    @Test
    public void addIngredientTwo() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        Assert.assertEquals(2,burger.ingredients.size());
    }

    @Test
    public void removeIngredientWhenSizeOne() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        Assert.assertEquals(0,burger.ingredients.size());
    }

    @Test
    public void removeIngredientWhenSizeMoreThanOne() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        Assert.assertEquals(1,burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1,0);

        Assert.assertEquals(ingredient2,burger.ingredients.get(0));
        Assert.assertEquals(ingredient,burger.ingredients.get(1));

    }

    @Test
    public void getPriceWithOneIngredient() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(10f);

        float expected = 210f;
        float actual = burger.getPrice();

        Assert.assertEquals(expected,actual, DELTA_ASSERT);
        Mockito.verify(bun,Mockito.times(1)).getPrice();
        Mockito.verify(ingredient,Mockito.times(1)).getPrice();
    }

    @Test
    public void getPriceWithTwoIngredient() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        Mockito.when(bun.getPrice()).thenReturn(100.01f);
        Mockito.when(ingredient.getPrice()).thenReturn(10.02f);
        Mockito.when(ingredient2.getPrice()).thenReturn(10.03f);

        float expected = 220.07f;
        float actual = burger.getPrice();

        Assert.assertEquals(expected,actual, DELTA_ASSERT);
        Mockito.verify(bun,Mockito.times(1)).getPrice();
        Mockito.verify(ingredient,Mockito.times(1)).getPrice();
        Mockito.verify(ingredient2,Mockito.times(1)).getPrice();
    }

    @Test
    public void getReceiptWithoutIngredient() {

        String bunName = "white bun";
        float price = 132.12f;

        Burger burger = Mockito.spy(new Burger());
        burger.setBuns(bun);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.doReturn(price).when(burger).getPrice();

        String expected = String.format("(==== %s ====)%n(==== %s ====)%n%nPrice: %f%n",
                bunName,bunName,price);
        String actual = burger.getReceipt();

        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getReceiptWithOneIngredient() {

        String bunName = "white bun";
        IngredientType ingredientType = IngredientType.SAUCE;
        String ingredientName = "hot sauce";
        float price = 999999.01f;

        Burger burger = Mockito.spy(new Burger());
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getType()).thenReturn(ingredientType);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        Mockito.doReturn(price).when(burger).getPrice();

        String expected = String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                bunName,ingredientType.name().toLowerCase(),ingredientName,bunName,price);
        String actual = burger.getReceipt();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getReceiptWithTwoIngredient() {

        String bunName = "white bun";
        IngredientType ingredientType = IngredientType.SAUCE;
        String ingredientName = "hot sauce";
        IngredientType ingredient2Type = IngredientType.FILLING;
        String ingredient2Name = "cutlet";
        float price = 100.03f;

        Burger burger = Mockito.spy(new Burger());
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getType()).thenReturn(ingredientType);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        Mockito.when(ingredient2.getType()).thenReturn(ingredient2Type);
        Mockito.when(ingredient2.getName()).thenReturn(ingredient2Name);
        Mockito.doReturn(price).when(burger).getPrice();

        String expected = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                bunName,ingredientType.name().toLowerCase(),ingredientName,ingredient2Type.name().toLowerCase(),
                ingredient2Name,bunName,price);
        String actual = burger.getReceipt();

        Assert.assertEquals(expected,actual);
    }
}