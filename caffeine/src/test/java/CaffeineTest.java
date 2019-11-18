import com.demo.Application;
import com.demo.CaffeineService;
import com.demo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author jjxiek
 * @since 2019/11/18 16:01
 */
@SpringBootTest(classes = Application.class)
public class CaffeineTest {
    @Autowired CaffeineService service;
    @Test
    public void fun1() throws InterruptedException {
        Person p1 = service.getPerson();
        System.out.println(p1);
        Person p2 = service.getPerson();
        System.out.println(p2);
        Thread.sleep(2000);
        Person p3 = service.getPerson();
        System.out.println(p3);
    }
    @Test
    public void fun2() {
        List<Person> list1 = service.listPerson();
        System.out.println(list1);
        List<Person> list2 = service.listPerson();
        System.out.println(list2);
    }

    @Test
    public void fun3() {
        // 报错,多个方法 不能使用同一个缓存
        Person p1 = service.getPerson();
        System.out.println(p1);
        Person p2 = service.getPerson();
        System.out.println(p2);
        List<Person> list1 = service.listPerson();
        System.out.println(list1);
        List<Person> list2 = service.listPerson();
        System.out.println(list2);
    }
    @Test
    public void fun4() {
        service.test();
        service.test();
    }
}
