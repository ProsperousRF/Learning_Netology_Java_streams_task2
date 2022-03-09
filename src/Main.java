import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Stanislav Rakitov in 2022
 */
public class Main {
  public static void main(String[] args) {
    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    List<String> families =
        Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    Collection<Person> persons = new ArrayList<>();
    for (int i = 0; i < 10_000_000; i++) {
      persons.add(
          new Person(
              names.get(new Random().nextInt(names.size())),
              families.get(new Random().nextInt(families.size())),
              new Random().nextInt(100),
              Sex.values()[new Random().nextInt(Sex.values().length)],
              Education.values()[new Random().nextInt(Education.values().length)]));
    }

    // Найти количество несовершеннолетних (т.е. людей младше 18 лет).
    long count = persons.stream().filter(p -> p.getAge() < 18).count();
//    System.out.println("количество несовершеннолетних (т.е. людей младше 18 лет) " + count);

    // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
    List<String> firstNames =
        persons.stream()
            .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
            .map(Person::getName)
            .collect(Collectors.toList());
    //    firstNames.forEach(System.out::println);

    // Получить отсортированный по фамилии список потенциально работоспособных людей с высшим
    // образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65
    // лет для мужчин).
    List<Person> highEducated =
        persons.stream()
            .filter(p -> Education.HIGHER.equals(p.getEducation()))
            .filter(p -> p.getAge() >= 18)
            .filter(
                p ->
                    (Sex.MAN.equals(p.getSex()) && p.getAge() < 65)
                        || (Sex.WOMAN.equals(p.getSex()) && p.getAge() < 60))
            .sorted(Comparator.comparing(Person::getFamily))
            .collect(Collectors.toList());

//    highEducated.forEach(System.out::println);
  }
}
