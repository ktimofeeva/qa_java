import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.Feline;
import com.example.Lion;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    Feline feline;

    @Test
    public void checkingTheMethodGetKittens() throws Exception {
        Lion lion = new Lion("Самец",feline);
        Mockito.when(feline.getKittens()).thenReturn(1);
        Assert.assertEquals(1, lion.getKittens());
    }

    @Test
    public void checkingTheMethodDoesHaveManeTrue() throws Exception{
        String sex = "Самец";
        Lion lion = new Lion(sex,feline);
        boolean actualResult = lion.doesHaveMane();
        assertEquals("Должен быть true", true, actualResult);
    }


    @Test
    public void checkingTheMethodDoesHaveManeFalse() throws Exception{
        String sex = "Самка";
        Lion lion = new Lion(sex,feline);
        boolean actualResult = lion.doesHaveMane();
        assertEquals("Должен быть false", false, actualResult);
    }

    @Test
    public void checkingTheMethodGetFood() throws Exception {
        String sex = "Самка";
        Lion lion = new Lion(sex, feline);
        List<String> expectedResult = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        assertEquals("Списки должны совпадать", expectedResult, lion.getFood());
    }

}