package com.example.pathfinder.service;

import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailedViewModel;
import com.example.pathfinder.model.view.RouteSummaryViewModel;

import java.util.List;

public interface RouteService {

    List<RouteSummaryViewModel> getAllRoutes();

    void addRoute(RouteServiceModel routeServiceModel);

    RouteDetailedViewModel findRouteById(Long id);
}
