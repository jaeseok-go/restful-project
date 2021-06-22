package com.jaeseok.restfulproject.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("UserInfo")
public class User {

    private Long id;

    @Min(value = 2, message = "name은 두 글자 이상 입력해 주세요.")
    private String name;

    @Past
    private Date joinDate;

    private String password;
    private String ssn;
}
