public class TestClass2 {

    @BeforeSuite
    void methodBefore() {
        System.out.println("\nТест 2 Бифо !");
    }

    @Test(priority = 8)
    void testingB() {
        System.out.println("Тест 2 - приоритет 8 ");
    }

    @Test(priority = 2)
    void testingBB() {
        System.out.println("Тест 2 - приоритет 2 ");
    }

    @AfterSuite
    void methodAfter() {
        System.out.println("Тест 2 Афтэ !");
    }

}