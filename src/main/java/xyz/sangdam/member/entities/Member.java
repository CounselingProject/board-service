package xyz.sangdam.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import xyz.sangdam.member.constants.UserType;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    private Long seq;

    private String gid;

    private String email;

    private String password;

    private String userName;

    private String mobile;

    private UserType userType;
}