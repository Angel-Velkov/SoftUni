package hiberspring.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.NONE)
public class EmployeesImportRootDto {

    @XmlElement(name = "employee")
    private List<EmployeeImportDto> employees;
}
