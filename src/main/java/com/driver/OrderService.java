package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;

    public void addOrder(Order order){

        orderRepository.addOrders(order);
    }
    public void addDeliveryPartner(String PartnerId){
        orderRepository.addDeliveryPartner(PartnerId);
    }
    public void createOrderPartnerPair(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }
    public Order findOrders(String orderId){
        return orderRepository.findOrder(orderId);
    }
    public DeliveryPartner findPartner(String partnerId){
        return orderRepository.findDeliveryPartner(partnerId);
    }
    public int getOrderCountFromPartnerId(String partnerId){
        return orderRepository.findOrderCountFromPartnerId(partnerId);
    }
    public List<String> getOrderFromPatnerId(String partnerId){
        return orderRepository.findOrderFromPartner(partnerId);
    }
    public List<String> getAllOrders(){
        return orderRepository.findAllOrders();
    }
    public int getCountOfUnassignedOrders(){

        return orderRepository.findCountOfUnassignedOrders();
    }
    public int getOrdersLeftAfterGivenTimeByPartner(String time, String partnerId){
        return orderRepository.findOrdersLeftAfterGivenTimeByPartner(time,partnerId );
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.findLastDeliveryTimeByPartner(partnerId);
    }


    public void deletePartnerById(String partnerId) {

        orderRepository.deletePartnerByPartnerId(partnerId);
    }


    public void deleteOrderById(String orderId){

        orderRepository.deleteOrderByOrderId(orderId);
    }


}
