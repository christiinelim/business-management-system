package com.bizorder.api;

import org.springframework.http.ResponseEntity;

import com.bizorder.model.Item;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Item", description = "Item APIs")
public interface ItemApi {
    
    // Read
    @Operation(
        summary = "Fetch all items",
        description = "Fetches details of all items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested items details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getAllItems();

    @Operation(
        summary = "Fetch item details by ID",
        description = "Fetches details of a specific item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested item details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getItem(@Parameter(description = "Item ID", required = true) Integer itemId);

    @Operation(
        summary = "Fetch items by account ID",
        description = "Fetches details of all items sold by a specific account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested account items retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getItemsByAccount(@Parameter(description = "Account ID", required = true) Integer accountId);

    // Create
    @Operation(
        summary = "Create item",
        description = "Creates a new item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item created"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> createItem(@Parameter(description = "Item details", required = true) Item item);

    // Update
    @Operation(
        summary = "Update item details",
        description = "Updates details of an existing item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item details updated"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> updateItem(@Parameter(description = "Item ID", required = true) Integer itemId, @Parameter(description = "Item details", required = true) Item item);

    // Delete
    @Operation(
        summary = "Delete item",
        description = "Deletes an existing item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item deleted"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> deleteItem(@Parameter(description = "Item ID", required = true) Integer itemId);
}
