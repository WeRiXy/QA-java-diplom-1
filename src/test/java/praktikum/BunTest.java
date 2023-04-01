package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {
    private String bunName;
    private float bunPrice;
    private Bun bun;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters(name = "{0} - {1}")
    public static Object[][] getData() {
        return new Object[][] {
                { new Faker().food().sushi(),(float) new Faker().number().randomDouble(2,1,100000) },
                { "", 0 },
                { new Faker().name().firstName(), Float.MAX_VALUE},
        };
    }

    @Before
    public void setUp() {
        bun = new Bun(bunName, bunPrice);
    }

    @Test
    public void getName() {
        String expected = bunName;
        String actual = bun.getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPrice() {
        float expected = bunPrice;
        float actual = bun.getPrice();

        Assert.assertEquals(expected, actual,0);
    }
}