package softuni.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerInfoDto {

    private String firstName;

    private String lastName;

    private Byte number;

    private BigDecimal salary;

    private String teamName;
}
