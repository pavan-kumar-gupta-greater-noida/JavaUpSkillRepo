import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class HclInterviewPractice {
    public static void main(String[] args) {
        // Java collections and equality example
        List<Employee> employees = List.of(
                new Employee(1, "Asha", 40000),
                new Employee(2, "Ravi", 52000),
                new Employee(3, "Mina", 68000)
        );

        Double avgSalary = employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0.0);
        System.out.println("Average salary: " + avgSalary);

        // HashMap issue example: mutable key is a bug
        Map<Person, String> map = new HashMap<>();
        Person p = new Person("John", 30);
        map.put(p, "Engineer");
        p.setAge(31);
        System.out.println("HashMap lookup after mutation: " + map.get(p));

        // Thread-safe counter example
        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executor.submit(counter::increment);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Counter value: " + counter.get());
    }

    record Employee(int id, String name, double salary) {}

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    static class Counter {
        private int value = 0;

        public synchronized void increment() {
            value++;
        }

        public synchronized int get() {
            return value;
        }
    }
}
