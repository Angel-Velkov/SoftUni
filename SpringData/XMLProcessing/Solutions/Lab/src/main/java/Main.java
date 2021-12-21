import model.Address;
import model.People;
import model.Person;
import model.PhoneNumber;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class Main {

    private static final String PEOPLE_XML_PATH = "src/main/resources/people.xml";

    public static void main(String[] args) {
        Address address1 = new Address("England", "Liverpool");
        Address address2 = new Address("France", "Paris");

        Person person1 = new Person("George", address1);
        person1.getPhoneNumbers().add(new PhoneNumber("0888 888 888"));

        Person person2 = new Person("Peter", address2);
        person2.getPhoneNumbers().add(new PhoneNumber("0123 456 789"));

        Person person3 = new Person("Dimitar", address1);

        People people = new People();
        people.getPeople().add(person1);
        people.getPeople().add(person2);
        people.getPeople().add(person3);

        // 1. Create JAXBContext
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(People.class);

            // 2. Create Marshaller
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // 3. Marshal to XML
            marshaller.marshal(people, new File(PEOPLE_XML_PATH));

            // 4. Create Unmarshaller
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // 5. Unmarshal to People
            FileInputStream fileInputStream = new FileInputStream(PEOPLE_XML_PATH);
            People unmarshalledPeople = (People) unmarshaller.unmarshal(fileInputStream);

            for (Person person : unmarshalledPeople.getPeople()) {
                System.out.printf(
                        "name: %-8.8s | country: %-10.10s | city: %-10.10s | phone number: %-10s%n",
                        person.getName(),
                        person.getAddress().getCountry(),
                        person.getAddress().getCity(),
                        person.getPhoneNumbers()
                                .stream()
                                .findFirst()
                                .orElse(new PhoneNumber())
                                .getNumber()
                );
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("kofti");
        }
    }
}
