package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ModelAndView stats() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("stats", this.statsService.getStats());
        modelAndView.setViewName("stats");

        return modelAndView;
    }
}
