import com.example.Feline;
import com.example.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    Feline feline;

    @Test
    public void checkingTheMethodGetSound(){
        Cat cat = new Cat(feline);
        String actualResult = cat.getSound();
        assertEquals("Должно быть Мяу","Мяу", actualResult);
    }

    @Test
    public void checkingTheMethodGetFood() throws Exception {
        Cat cat = new Cat(feline);
        List<String> expectedResult = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        assertEquals("Списки должны совпадать", expectedResult, cat.getFood());
    }
}
