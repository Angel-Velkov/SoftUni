package bg.softuni.mobilelele.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;

    public void clean() {
        this.id = null;
        this.username = null;
    }
}
