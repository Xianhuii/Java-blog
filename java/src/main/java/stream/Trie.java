package stream;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Trie<E> {
    /**
     * 自定义Trie的元素分隔器
     * @param <E>
     */
    static final class TrieSpliterator<E> implements Spliterator<E> {

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            return false;
        }

        @Override
        public Spliterator<E> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }

    public Stream<E> stream() {
        return StreamSupport.stream(new TrieSpliterator<E>(), false);
    }
}
