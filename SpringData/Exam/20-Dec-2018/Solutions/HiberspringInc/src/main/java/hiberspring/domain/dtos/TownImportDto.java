package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
public class TownImportDto {

    @NotBlank
    @Expose
    private String name;

    @NotNull
    @PositiveOrZero
    @Expose
    private Integer population;
}
