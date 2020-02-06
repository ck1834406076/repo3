package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String sname;
    private Integer age;
    private Integer gender;
    private String address;
    private String gradeid;
}
