package stanislav.danylenko.model;

import java.time.LocalDateTime;

public class Person {

    private final Long id;
    private final LocalDateTime createdAt;

    private String name;
    private Integer age;
    private Boolean isMarried;

    public Person(Long id, String name, Integer age, Boolean isMarried, LocalDateTime createdAt) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
    }
}
