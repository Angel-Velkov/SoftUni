package hiberspring.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeImportDto {

    @NotBlank
    @XmlAttribute(name = "first-name")
    private String firstName;

    @NotBlank
    @XmlAttribute(name = "last-name")
    private String lastName;

    @NotBlank
    @XmlAttribute
    private String position;

    @NotBlank
    @XmlElement(name = "card")
    private String cardNumber;

    @NotBlank
    @XmlElement(name = "branch")
    private String branchName;
}
