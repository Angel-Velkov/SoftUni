package Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException
            , InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<Person> personClass = Person.class;
        //private constructor
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person person = constructor.newInstance("Angel", 19);

        //By the name of the field
        Field age = personClass.getDeclaredField("age");

        //OR

        //It'll return first Integer field (in this case age field)
        Field intField = Arrays.stream(personClass.getDeclaredFields())
                .filter(f -> f.getType() == int.class)
                .findFirst()
                .orElse(null);

        age.setAccessible(true);

        //Returns Object, so we use type casting
        int oldValue = (int) age.get(person); //19

        age.set(person, 20); //20

        Modifier.toString(1);
    }
}



