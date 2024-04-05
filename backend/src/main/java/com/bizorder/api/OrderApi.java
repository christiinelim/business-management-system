package com.bizorder.api;

import org.springframework.http.ResponseEntity;

import com.bizorder.model.Order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order", description = "Order APIs")
public interface OrderApi {
    
    // Read
    @Operation(
        summary = "Fetch all orders",
        description = "Fetches details of all orders")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested orders details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getAllOrders();

    @Operation(
        summary = "Fetch order details by ID",
        description = "Fetches details of a specific order by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested order details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getOrder(@Parameter(description = "Order ID", required = true) Integer orderId);

    @Operation(
        summary = "Fetch orders by customer account ID",
        description = "Fetches details of all orders associated with a specific customer account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested orders retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getOrderByAccount(@Parameter(description = "Customer Account ID", required = true) Integer accountId);

    // Create
    @Operation(
        summary = "Create new order",
        description = "Creates a new order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order created"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> createOrder(@Parameter(description = "Order details", required = true) Order order);

    // Update
    @Operation(
        summary = "Update order details",
        description = "Updates details of an existing order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order details updated"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> updateOrder(@Parameter(description = "Order ID", required = true) Integer orderId, @Parameter(description = "Order details", required = true) Order order);

    // Delete
    @Operation(
        summary = "Delete order",
        description = "Deletes an existing order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order deleted"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> deleteOrder(@Parameter(description = "Order ID", required = true) Integer orderId);
}
