package xyz.sangdam.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import xyz.sangdam.member.constants.Authority;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authorities {

    private Authority authority;
}