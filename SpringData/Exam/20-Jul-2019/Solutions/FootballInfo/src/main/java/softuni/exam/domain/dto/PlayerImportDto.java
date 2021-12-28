package softuni.exam.domain.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.domain.enums.Position;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class PlayerImportDto {

    @Expose
    @NotBlank
    private String firstName;

    @Expose
    @NotBlank
    @Size(min = 3, max = 15)
    private String lastName;

    @Expose
    @NotNull
    @Min(1)
    @Max(99)
    private Byte number;

    @Expose
    @PositiveOrZero
    private BigDecimal salary;

    @Expose
    @NotNull
    private Position position;

    @Expose
    @NotNull
    private PictureImportDto picture;

    @Expose
    @NotNull
    private TeamImportDto team;
}
