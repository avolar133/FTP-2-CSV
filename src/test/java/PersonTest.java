import myPackage.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void testAge(){

        Person person = new Person("rick", 22);

        int age= person.getAge();

        assertEquals(age, 22);

    }
}
