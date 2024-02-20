package com.studies.course.services;

import com.studies.course.entities.Order;
import com.studies.course.entities.User;
import com.studies.course.entities.enums.OrderStatus;
import com.studies.course.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    void findAll() {
        Order o1 = new Order(1L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, new User());
        Order o2 = new Order(2L, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, new User());

        List<Order> orders = Arrays.asList(o1, o2);

        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.findAll();

        Assertions.assertEquals(orders, result);
    }

    @Test
    void findById() {
        Order o1 = new Order(1L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, new User());

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(o1));

        o1 = orderService.findById(1L);

        Assertions.assertNotNull(o1);
        Assertions.assertEquals(1L, o1.getId());
        Assertions.assertEquals("2019-06-20T19:53:07Z", o1.getMoment().toString());
        Assertions.assertEquals(OrderStatus.PAID, o1.getOrderStatus());
    }

    @Test
    void findByIdNotFound(){

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Order o1 = orderService.findById(1L);

        Assertions.assertNull(o1);
    }
}