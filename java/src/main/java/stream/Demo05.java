package stream;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Demo05 {
    public static void demo01() {
        String[] strings = new String[]{"1","2","3","4"};
        Spliterator<String> stringSpliterator = Arrays.spliterator(strings);
        Stream<String> parallelStringStream = StreamSupport.stream(stringSpliterator, true);
    }
    public static void main(String[] args) {

    }
}
