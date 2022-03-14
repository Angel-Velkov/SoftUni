package bg.softuni.mobilelele.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class OfferDetailedViewModel extends OfferSummaryViewModel {
    private LocalDateTime created;
    private LocalDateTime modified;
    private String sellerFirstName;
    private String sellerLastName;
}
