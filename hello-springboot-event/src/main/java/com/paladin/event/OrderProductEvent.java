package com.paladin.event;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Data
@ToString
public class OrderProductEvent extends ApplicationEvent {

    /** 该类型事件携带的信息 */
    private String orderId;

    public OrderProductEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }
}