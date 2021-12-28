package softuni.exam.domain.dto;

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
@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.NONE)
public class TeamsImportRootDto {

    @XmlElement(name = "team")
    private List<TeamImportDto> teams;
}
