package hiberspring.service.impl;

import hiberspring.domain.dtos.ProductImportDto;
import hiberspring.domain.dtos.ProductsImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.ProductRepository;
import hiberspring.service.BranchService;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_XML_FILES_PATH = PATH_TO_FILES + "products.xml";

    private final ProductRepository productRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final BranchService branchService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              FileUtil fileUtil, XmlParser xmlParser,
                              ValidationUtil validationUtil, BranchService branchService,
                              ModelMapper modelMapper) {

        this.productRepository = productRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.branchService = branchService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean productsAreImported() {
        return productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return fileUtil.readFile(PRODUCTS_XML_FILES_PATH);
    }

    @Override
    public String importProducts() throws JAXBException {
        ProductsImportRootDto rootDto =
                xmlParser.parseXml(ProductsImportRootDto.class, PRODUCTS_XML_FILES_PATH);

        StringBuilder importResult = new StringBuilder();

        for (ProductImportDto productDto : rootDto.getProducts()) {
            boolean isValid = validationUtil.isValid(productDto);

            if (isValid) {
                Branch branch = branchService.findBranchByName(productDto.getBranchName());

                isValid = branch != null;

                if (isValid) {
                    Product product = modelMapper.map(productDto, Product.class);
                    product.setBranch(branch);

                    productRepository.save(product);

                    importResult.append(
                            String.format(SUCCESSFUL_IMPORT_MESSAGE, "Product", product.getName())
                    );
                }
            }

            if (!isValid) {
                importResult.append(INCORRECT_DATA_MESSAGE);
            }
        }

        return importResult.toString().trim();
    }
}
