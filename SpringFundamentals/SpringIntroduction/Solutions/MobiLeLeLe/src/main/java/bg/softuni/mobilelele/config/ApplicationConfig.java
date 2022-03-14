package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.OfferServiceModel;
import bg.softuni.mobilelele.model.view.OfferSummaryViewModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<EngineEnum, String> engineTypeConverter = ctx -> ctx.getSource() == null
                ? "Unknown"
                : ctx.getSource().getAsString();

        Converter<TransmissionEnum, String> transmissionTypeConverter = ctx -> ctx.getSource() == null
                ? "Unknown"
                : ctx.getSource().getAsString();

        modelMapper.addConverter(engineTypeConverter);
        modelMapper.addConverter(transmissionTypeConverter);

        modelMapper.createTypeMap(OfferEntity.class, OfferServiceModel.class)
                .addMappings(m -> {
                    m.using(engineTypeConverter).map(OfferEntity::getEngine, OfferServiceModel::setEngineType);
                    m.using(transmissionTypeConverter).map(OfferEntity::getTransmission, OfferServiceModel::setTransmissionType);
                });

        return modelMapper;
    }
}
