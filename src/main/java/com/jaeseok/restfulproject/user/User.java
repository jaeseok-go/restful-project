package com.jaeseok.restfulproject.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    @Min(value = 2, message = "name은 두 글자 이상 입력해 주세요.")
    private String name;

    @Past
    private Date joinDate;
}
