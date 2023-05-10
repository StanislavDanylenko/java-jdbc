package stanislav.danylenko;

import stanislav.danylenko.model.Person;
import stanislav.danylenko.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository();

//        Person person = new Person("John", 25, true, LocalDateTime.now());
//        personRepository.save(person);

        List<Person> people = personRepository.findAll();
    }
}