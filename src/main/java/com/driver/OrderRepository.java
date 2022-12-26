package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    private HashMap<String, Order> OrdersList;
    private HashMap<String,DeliveryPartner> DeliveryPartnerList;
    private HashMap<String, List<String>> OrderPartnerList;

    public OrderRepository(){
        this.OrdersList = new HashMap<String,Order>();
        this.DeliveryPartnerList = new HashMap<String, DeliveryPartner>();
        this.OrderPartnerList = new HashMap<String, List<String>>();
    }
    public void addOrders(Order order){

        OrdersList.put(order.getId(),order);
    }
    public void addDeliveryPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        DeliveryPartnerList.put(partnerId, deliveryPartner);
    }
    public void addOrderPartnerPair(String orderId, String deliveryPartnerId){
        if(OrdersList.containsKey(orderId) && DeliveryPartnerList.containsKey(deliveryPartnerId)){
            OrdersList.put(orderId, OrdersList.get(orderId));
            DeliveryPartnerList.put(deliveryPartnerId, DeliveryPartnerList.get(deliveryPartnerId));
            List<String> currentOrder = new ArrayList<String>();
            if(OrderPartnerList.containsKey(deliveryPartnerId)) currentOrder = OrderPartnerList.get(deliveryPartnerId);
            currentOrder.add(orderId);
            OrderPartnerList.put(deliveryPartnerId, currentOrder);
        }
    }
    public Order findOrder(String orderId){
        return OrdersList.get(orderId);
    }
    public DeliveryPartner findDeliveryPartner(String partnerId){
        return DeliveryPartnerList.get(partnerId);
    }
    public int findOrderCountFromPartnerId(String partnerId){
        int count =0;
        if(DeliveryPartnerList.containsKey(partnerId)){

            count =OrderPartnerList.get(partnerId).size();
        }
        return count;
    }
    public List<String> findOrderFromPartner(String partnerId){
        List<String> orders = new ArrayList<String>();
        if(OrderPartnerList.containsKey(partnerId)) orders = OrderPartnerList.get(partnerId);
        return orders;
    }
    public List<String> findAllOrders(){

        return new ArrayList<>(OrdersList.keySet());
    }
    public int findCountOfUnassignedOrders(){
        int count = 0;
        for( String partner : OrderPartnerList.keySet()){
            count = count + OrderPartnerList.get(partner).size();
        }
        return (OrdersList.size()-count);
    }
    public int findOrdersLeftAfterGivenTimeByPartner(String time, String partnerId){

        int count = 0;

        int time1 = (Integer.parseInt(time.substring(0,2))*60) +
                (Integer.parseInt(time.substring(time.length()-2,time.length())));

        for(String order : OrderPartnerList.get(partnerId) ){
            if(OrdersList.get(order).getDeliveryTime() > time1){
                count++;
            }
        }

        return count;
    }
    public String findLastDeliveryTimeByPartner(String partnerId){

        int time = 0;
        for(String order : OrderPartnerList.get(partnerId) ){
            if(time<OrdersList.get(order).getDeliveryTime()){
                time = OrdersList.get(order).getDeliveryTime();
            }
        }

        int x= time/60;
        String a=String.format("%2d",x);
        String b=String.format("%2d",time%60);

        return a+":"+b;
    }
    public void deletePartnerByPartnerId(String partnerId){

        if(OrderPartnerList.containsKey(partnerId)){
            for(String order : OrderPartnerList.get(partnerId) ){
                if(OrdersList.containsKey(order)){
                    OrdersList.remove(order);
                }
            }
        }
        OrderPartnerList.remove(partnerId);
        if(DeliveryPartnerList.containsKey(partnerId)){
            DeliveryPartnerList.remove(partnerId);
        }

    }
    public void deleteOrderByOrderId(String orderId){

        List<String> list =new ArrayList<>();
        for(String partner : OrderPartnerList.keySet()){
            for(String order : OrderPartnerList.get(partner)){
                if(order.equals(orderId)){
                    OrderPartnerList.get(partner).remove(order);
                    break;
                }
            }
        }
        if(OrdersList.containsKey(orderId)){
            OrdersList.remove(orderId);
        }
    }








}
