package com.bizorder.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.bizorder.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer", description = "Customer APIs")
public interface CustomerApi {

    // READ
    @Operation(
        summary = "Fetch customer details by ID",
        description = "Fetches details of a specific customer by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested customer details retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getCustomer(@PathVariable("customerId") Integer customerId);


    // READ
    @Operation(
        summary = "Fetch all customers",
        description = "Fetches details of all customers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requested customer list retrieved"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getAllCustomers();


    // CREATE
    @Operation(
        summary = "Create new customer",
        description = "Creates a new customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer created successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> createCustomer(@RequestBody Customer customer);


    // UPDATE
    @Operation(
        summary = "Update customer details",
        description = "Updates details of an existing customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> updateCustomer(
        @Parameter(description = "Customer ID to update", required = true) @PathVariable("customerId") Integer customerId,
        @RequestBody Customer customer);


    // DELETE
    @Operation(
        summary = "Delete customer",
        description = "Deletes an existing customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> deleteCustomer(
        @Parameter(description = "Customer ID to delete", required = true) @PathVariable("customerId") Integer customerId);
}
