package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class BranchImportDto {

    @NotBlank
    @Expose
    private String name;

    @NotBlank
    @Expose
    private String town;
}
