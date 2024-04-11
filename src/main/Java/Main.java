import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, String> map = new HashMap<>();


    public static void main(String[] args) {
//        инициализирует необходимые сервисы, репозитории и контроллеры,
//        и обеспечивает взаимодействие с пользователем через консоль.
        String s;
        putToMap(1, "one");
        putToMap(1, "two");
        putToMap(2, null);
        remove(3);
        System.out.println(map.toString());
        System.out.println(map.get(3));
    }

    public static void putToMap(int k, String string) {
        map.put(k, string);
    }

    public static void remove(int k) {
        map.remove(k);
    }
}
