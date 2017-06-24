import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainClass {

    public static void main(String[] args) throws IllegalAccessException,
            InvocationTargetException, InstantiationException, ClassNotFoundException {
        start(Class.forName("TestClass1"));
        start(Class.forName("TestClass2"));
    }


    static void start(Class aClass) throws IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Object o = aClass.newInstance();
        Method[] arrM = aClass.getDeclaredMethods();

        // Чтобы сортировать тесты @Test, создадим дерево:
        TreeMap<Integer, Method> methodsTree = new TreeMap<Integer, Method>();

        // посчитаем сколько @Before и After:
        int beforeCount = 0, afterCount = 0;

        // @Before и @After запустим по их индексам в массиве:
        int beforeIndex = -1, afterIndex = -1;


        for (int i = 0; i < arrM.length; i++) {

            if (arrM[i].getAnnotation(BeforeSuite.class) != null) {
                beforeCount++;
                beforeIndex = i;
            }
            if (arrM[i].getAnnotation(AfterSuite.class) != null) {
                afterCount++;
                afterIndex = i;
            }
            // если @Before или @After suite методы окажутся НЕ в единственном экземпляре,
            // бросим исключение:
            if (beforeCount > 1 || afterCount > 1)
                throw new RuntimeException("Before-After suite exc!");

            if (arrM[i].getAnnotation(Test.class) != null) {
                methodsTree.put(arrM[i].getAnnotation(Test.class).priority(), arrM[i]);
            }
        }


        // выполняем @Before
        if (beforeIndex > -1) arrM[beforeIndex].invoke(o);

        // выполняем @Test
        for (Map.Entry<Integer, Method> entry : methodsTree.entrySet()) {
            entry.getValue().invoke(o);
        }

        // выполняем @After
        if (afterIndex > -1) arrM[afterIndex].invoke(o);
    }
}