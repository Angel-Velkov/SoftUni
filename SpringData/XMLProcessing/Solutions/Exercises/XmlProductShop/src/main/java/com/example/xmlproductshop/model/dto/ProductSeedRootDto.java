package com.example.xmlproductshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@Data
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductSeedRootDto {

    @XmlElement(name = "product")
    private List<ProductSeedDto> products;
}
