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
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductsImportRootDto {

    @XmlElement(name = "product")
    private List<ProductImportDto> products;
}