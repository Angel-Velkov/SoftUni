package bg.softuni.mobilelele.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatsView {
    private int anonymousRequests;
    private int authRequests;

    public int getTotalRequests() {
        return this.anonymousRequests + this.anonymousRequests;
    }
}
