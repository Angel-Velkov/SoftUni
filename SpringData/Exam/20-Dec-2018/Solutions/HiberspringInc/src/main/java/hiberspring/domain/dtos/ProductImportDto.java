package hiberspring.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDto {

    @NotBlank
    @XmlAttribute
    private String name;

    @NotNull
    @PositiveOrZero
    @XmlAttribute
    private Integer clients;

    @NotBlank
    @XmlElement(name = "branch")
    private String branchName;
}
