public class TestClass1 {

    @BeforeSuite
    void methodBefore() {
        System.out.println("\nТест 1 Бифо !");
    }

    @Test(priority = 10)
    void testingA() {
        System.out.println("Тест 1 - приоритет 10 ");
    }

    @Test(priority = 1)
    void testingAA() {
        System.out.println("Тест 1 - приоритет 1 ");
    }

    @AfterSuite
    void methodAfter() {
        System.out.println("Тест 1 Афтэ !");
    }
}