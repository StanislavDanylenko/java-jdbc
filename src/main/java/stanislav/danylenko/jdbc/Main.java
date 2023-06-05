package stanislav.danylenko.jdbc;

import stanislav.danylenko.jdbc.model.Person;
import stanislav.danylenko.jdbc.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository();

        Person person = new Person(null, "John", 25, true, LocalDateTime.now());
        personRepository.save(person);

        List<Person> jo = personRepository.findByName("Jo");

        Person personUpdated = jo.get(0);
        personUpdated.setName("Newname");
        personUpdated.setMarried(false);
        personUpdated.setAge(35);
        personRepository.update(personUpdated);

        jo = personRepository.findByName("nam");

        personRepository.deleteById(personUpdated.getId());

        List<Person> people = personRepository.findAll();
    }
}