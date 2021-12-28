package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PictureImportDto;
import softuni.exam.domain.dto.PicturesImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static softuni.exam.common.Constants.*;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURES_XML_FILES_PATH = PATH_TO_FILES_XML + "pictures.xml";

    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, FileUtil fileUtil,
                              XmlParser xmlParser, ValidatorUtil validatorUtil,
                              ModelMapper modelMapper) {

        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importPictures() throws JAXBException {
        PicturesImportRootDto rootDto =
                xmlParser.parseXml(PicturesImportRootDto.class, PICTURES_XML_FILES_PATH);

        StringBuilder importResult = new StringBuilder();

        for (PictureImportDto pictureDto : rootDto.getPictures()) {

            if (validatorUtil.isValid(pictureDto)) {
                Picture picture = modelMapper.map(pictureDto, Picture.class);
                pictureRepository.save(picture);

                importResult.append(
                        String.format(SUCCESSFUL_IMPORT_MESSAGE,
                                "picture", picture.getUrl())
                );
            } else {
                importResult.append(
                        String.format(INCORRECT_DATA_MESSAGE, "picture")
                );
            }

            importResult.append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return fileUtil.readFile(PICTURES_XML_FILES_PATH);
    }

    @Override
    public Picture findPictureByUrl(String url) {
        return pictureRepository.findByUrl(url).orElse(null);
    }
}
