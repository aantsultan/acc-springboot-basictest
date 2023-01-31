package com.springboot.basictest.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class WidgetOrderDto {

    @NotNull
    @Pattern(regexp = "^[0-5],[0-5],[0-5],[0-5],[0-5]$", message = "Invalid value for field widget_order, rejected value: ${validatedValue}")
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
