import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> namesList = Arrays.asList("Ivan", "John", "Peter", "Alice", "Bob", "Eve");
        String formattedNames = formatNames(namesList);
        System.out.println("Завдання 1: " + formattedNames);


        List<String> stringsList = Arrays.asList("apple", "banana", "grape", "orange");
        List<String> sortedUpperCaseList = sortAndToUpper(stringsList);
        System.out.println("Завдання 2: " + sortedUpperCaseList);


        String[] inputArray = {"1, 2, 0", "4, 5"};
        String sortedAndJoinedNumbers = sortAndJoinNumbers(inputArray);
        System.out.println("Завдання 3: " + sortedAndJoinedNumbers);


        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long seed = 123456789;
        Stream<Long> randomStream = linearCongruentialGenerator(a, c, m, seed);
        randomStream.limit(10).forEach(System.out::println);

       
        Stream<String> stream1 = Stream.of("a", "b", "c");
        Stream<String> stream2 = Stream.of("x", "y", "z");

        Stream<String> zippedStream = zip(stream1, stream2);
        System.out.println("Завдання 5: ");
        zippedStream.forEach(System.out::println);
    }

    public static String formatNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> (i / 2 + 1) + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }

    public static List<String> sortAndToUpper(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .sorted((s1, s2) -> s2.compareTo(s1))
                .collect(Collectors.toList());
    }

    public static String sortAndJoinNumbers(String[] inputArray) {
        return Arrays.stream(inputArray)
                .flatMap(s -> Arrays.stream(s.split(",\\s*")))
                .map(Integer::parseInt)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> linearCongruentialGenerator(long a, long c, long m, long seed) {
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }


    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while (iterator1.hasNext() || iterator2.hasNext()) {
            if (iterator1.hasNext()) {
                builder.accept(iterator1.next());
            }

            if (iterator2.hasNext()) {
                builder.accept(iterator2.next());
            }
        }

        return builder.build();
    }

}





