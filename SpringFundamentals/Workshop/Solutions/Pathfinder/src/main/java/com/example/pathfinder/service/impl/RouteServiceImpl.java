package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.PictureEntity;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper mapper;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper mapper) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RouteViewModel> getAllRoutes() {
        return this.routeRepository.findAll()
                .stream()
                .map(route -> {
                    RouteViewModel routeView = this.mapper.map(route, RouteViewModel.class);

                    routeView.setPictureUrl(
                            route.getPictures()
                                    .stream()
                                    .findFirst()
                                    .get()
                                    .getUrl()
                    );

                    return routeView;
                })
                .collect(Collectors.toList());
    }
}
