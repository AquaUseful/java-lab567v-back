package org.psu.lab567.service;

import javax.persistence.EntityNotFoundException;

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

    public BuyOrder getById(@NonNull Long id) {
        final BuyOrder order = buyOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return order;
    }

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

    public void patchById(@NonNull Long id, @NonNull NewBuyOrderRequest patch) {
        BuyOrder order = this.getById(id);
        order.setProduct(patch.getProduct());
        order.setAddress(patch.getAddress());
        order.setComment(patch.getComment());
        buyOrderRepository.save(order);
    }

    public boolean isAuthorById(@NonNull Long orderId, @NonNull User authorToTest) {
        final BuyOrder order = getById(orderId);
        return order.getAuthor().equals(authorToTest);
    }

    public void deleteById(@NonNull Long id) {
        buyOrderRepository.deleteById(id);
    }
}
