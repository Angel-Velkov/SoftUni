package hiberspring.util.init;

import hiberspring.util.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParserImpl implements XmlParser {

    private JAXBContext jaxbContext;

    @SuppressWarnings("unchecked")
    @Override
    public <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (O) unmarshaller.unmarshal(new File(filePath));
    }
}
