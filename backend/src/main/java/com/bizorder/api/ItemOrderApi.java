package com.bizorder.api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

import com.bizorder.model.ItemOrder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Items placed in an Order", description = "Item Order APIs")
public interface ItemOrderApi {
    // Read
    @Operation(
        summary = "Fetch purchase details by ID",
        description = "Fetches details of a specific purchase by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested purchase details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getPurchase(@Parameter(description = "Purchase ID", required = true) Integer purchaseId);

    @Operation(
        summary = "Fetch items by order ID",
        description = "Fetches details of all items in a specific order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested order details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getItemsByOrder(@Parameter(description = "Order ID", required = true) Integer orderId);

    // Create
    @Operation(
        summary = "Create item order",
        description = "Creates a new item order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Purchase created"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> createItemOrder(@Parameter(description = "Item order details", required = true) ItemOrder itemOrder);

    // Update
    @Operation(
        summary = "Update item order details",
        description = "Updates details of an existing item order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Purchase details updated"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> updateItemOrder(@Parameter(description = "Purchase ID", required = true) Integer purchaseId, @Parameter(description = "Item order details", required = true) ItemOrder itemOrder);

    // Delete
    @Operation(
        summary = "Delete item order",
        description = "Deletes an existing item order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item order deleted"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> deleteItemOrder(@Parameter(description = "Purchase ID", required = true) Integer purchaseId);
}