package org.psu.lab567.controller;

import java.util.Collection;

import org.psu.lab567.model.BuyOrder;
import org.psu.lab567.model.User;
import org.psu.lab567.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "order")
public class BuyOrderController {
    @Autowired
    private AuthService authService;

    @GetMapping(path = "")
    public ResponseEntity<Collection<BuyOrder>> getAllOrders() {
        final User self = authService.getSelf();
        final Collection<BuyOrder> orders = self.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
