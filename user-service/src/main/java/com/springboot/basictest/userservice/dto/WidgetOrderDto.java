package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WidgetOrderDto {

    @JsonProperty("widget_order")
    private String widgetOrder;

    public String getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(String widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public WidgetOrderDto(String widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public WidgetOrderDto() {
    }
}
