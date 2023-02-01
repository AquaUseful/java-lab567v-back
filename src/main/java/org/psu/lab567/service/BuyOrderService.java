package org.psu.lab567.service;

import org.psu.lab567.model.BuyOrder;
import org.psu.lab567.model.User;
import org.psu.lab567.pojo.NewBuyOrderRequest;
import org.psu.lab567.repository.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class BuyOrderService {
    @Autowired
    private BuyOrderRepository buyOrderRepository;

    public void save(@NonNull BuyOrder order) {
        buyOrderRepository.save(order);
    }

    public void fromRequest(@NonNull NewBuyOrderRequest request, @NonNull User author) {
        final BuyOrder order = new BuyOrder(
                request.getProduct(),
                request.getAddress(),
                request.getComment(),
                author);
        buyOrderRepository.save(order);
    }
}
