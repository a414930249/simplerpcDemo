import java.io.Serializable;

/**
 * Created by tianzhiwei on 2014/10/3.
 */
public class Student implements Serializable {
    private Integer Id;
    private String name;
    private Integer age;

    public Student(){

    }
    public Student(String name,Integer Id,Integer age){
        super();
        this.setId(Id);
        this.setAge(age);
        this.setName(name);
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
