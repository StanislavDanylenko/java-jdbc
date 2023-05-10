package stanislav.danylenko.model;

import java.time.LocalDateTime;

public class Person {

    private final String name;
    private final Integer age;
    private final Boolean isMarried;
    private final LocalDateTime createdAt;

    public Person(String name, Integer age, Boolean isMarried, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getMarried() {
        return isMarried;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
