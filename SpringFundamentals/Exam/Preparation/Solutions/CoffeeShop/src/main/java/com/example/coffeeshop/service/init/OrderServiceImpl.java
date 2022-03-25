package com.example.coffeeshop.service.init;

import com.example.coffeeshop.model.entity.OrderEntity;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper mapper,
                            UserService userService, CategoryService categoryService) {

        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity orderEntity = this.mapper.map(orderServiceModel, OrderEntity.class);

        orderEntity.setCategory(this.categoryService.findCategoryByName(orderServiceModel.getCategory()));
        orderEntity.setEmployee(this.userService.getCurrentLoggedInUser());

        this.orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderViewModel> findAllOrders() {
        return this.orderRepository.findAllOrderedByPriceDesc()
                .stream()
                .map(order -> {
                    OrderViewModel orderView = this.mapper.map(order, OrderViewModel.class);
                    orderView.setCategory(order.getCategory().getName());

                    return orderView;
                })
                .collect(Collectors.toList());
    }

    @Modifying
    @Override
    public void readyOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}
