package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.PictureEntity;
import com.example.pathfinder.model.entity.RouteEntity;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailedViewModel;
import com.example.pathfinder.model.view.RouteSummaryViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final CategoryService categoryService;
    private final RouteRepository routeRepository;
    private final ModelMapper mapper;

    @Autowired
    public RouteServiceImpl(CategoryService categoryService,
                            RouteRepository routeRepository,
                            ModelMapper mapper) {

        this.categoryService = categoryService;
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RouteSummaryViewModel> getAllRoutes() {
        return this.routeRepository.findAll()
                .stream()
                .map(route -> {
                    RouteSummaryViewModel routeView = this.mapper.map(route, RouteSummaryViewModel.class);

                    routeView.setPictureUrl(
                            route.getPictures()
                                    .stream()
                                    .findFirst()
                                    .map(PictureEntity::getUrl)
                                    .orElse("/images/add-img.png")
                    );

                    return routeView;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addRoute(RouteServiceModel routeServiceModel) {
        RouteEntity routeEntity = this.mapper.map(routeServiceModel, RouteEntity.class);
        // TODO: Set current user

        routeEntity.setCategories(
                this.categoryService
                        .findAllCategoriesWithNameIn(routeServiceModel.getCategories())
        );

        this.routeRepository.save(routeEntity);
    }

    @Override
    public RouteDetailedViewModel findRouteById(Long id) {
        RouteEntity routeEntity = this.routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no such route"));

        RouteDetailedViewModel routeView = this.mapper.map(routeEntity, RouteDetailedViewModel.class);
        routeView.setPictureUrls(
                routeEntity
                        .getPictures()
                        .stream()
                        .map(PictureEntity::getUrl)
                        .collect(Collectors.toSet())
        );

        return routeView;
    }
}
