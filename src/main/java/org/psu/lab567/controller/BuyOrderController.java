package org.psu.lab567.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.psu.lab567.model.BuyOrder;
import org.psu.lab567.model.User;
import org.psu.lab567.pojo.NewBuyOrderRequest;
import org.psu.lab567.service.AuthService;
import org.psu.lab567.service.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

@RestController
@RequestMapping(path = "order")
public class BuyOrderController {
    @Autowired
    private AuthService authService;
    @Autowired
    private BuyOrderService buyOrderService;

    @GetMapping(path = "self")
    public ResponseEntity<Collection<BuyOrder>> getAllOrders() {
        final User self = authService.getSelf();
        final Collection<BuyOrder> orders = self.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping(path = "self")
    public ResponseEntity<Void> postNewOrder(@NonNull @RequestBody @Valid NewBuyOrderRequest request) {
        final User self = authService.getSelf();
        buyOrderService.fromRequest(request, self);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
