import com.example.Feline;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class FelineTest {
    private final int number;

    public FelineTest(int number){
        this.number = number;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getSumData() {
        return new Object[][]{
                {1},
                {2},
                {3},
        };
    }

    @Test
    public void  checkingTheMethodGetKittens() {
        Feline feline = new Feline();
        int actualResult = feline.getKittens(number);
        assertEquals("Значения должны быть равны", number, actualResult);
    }

    @Test
    public void checkingTheMethodGetFamily() {
        Feline feline = new Feline();
        String actualResult = feline.getFamily();
        assertEquals("Должно быть Кошачьи","Кошачьи", actualResult);

    }

    @Test
    public void checkingTheMethodGetKittensOne(){
        Feline feline = new Feline();
        int actualResult = feline.getKittens();
        assertEquals("Должна быть еденица", 1, actualResult);
    }

    @Test
    public void checkingTheMethodEatMeat() throws Exception {
        Feline feline = new Feline();
        List<String> actualResult = List.of("Животные", "Птицы", "Рыба");
        assertEquals("Списки должны совпадать", actualResult, feline.eatMeat());
    }
}