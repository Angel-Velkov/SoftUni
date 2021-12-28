package softuni.exam.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TeamViewDto {

    private String name;

    private List<PlayerViewDto> players;
}
