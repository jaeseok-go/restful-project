package com.jaeseok.restfulproject.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @JsonFilter("UserInfo")

@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {

    private Long id;

    @Min(value = 2, message = "name은 두 글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자 등록일 입력해 주세요.")
    private Date joinDate;

    @ApiModelProperty(notes = "사용자 패스워드를 입력해 주세요.")
    private String password;

    @ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요.")
    private String ssn;
}
