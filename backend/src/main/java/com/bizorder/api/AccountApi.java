package com.bizorder.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bizorder.model.Account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account", description = "User account APIs")
public interface AccountApi {
    
    // READ
    @Operation(
        summary = "Fetch all accounts registered",
        description = "Fetches all account entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully fetched all account details", content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getAllAccounts();


    // READ 
    @Operation(
        summary = "Fetch account of a user registered",
        description = "Fetches only a specific account and their data from data source using account id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully fetched account detail of specified user", content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> getAccount(
        @Parameter(description = "Account ID to fetch") @PathVariable("accountId") Integer accountId);

    
    // UPDATE
    @Operation(
        summary = "Update account details of a user",
        description = "Updates only a specific account using account id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated detail of specified user", content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> updateAccount(
        @Parameter(description = "Account ID to update") @PathVariable("accountId") Integer accountId,
        @RequestBody Account account);

    
    // DELETE
    @Operation(
        summary = "Delete account of a user",
        description = "Deletes only a specific account using account id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "403", description = "Forbidden request due to invalid JWT"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> deleteAccount(
        @Parameter(description = "Account ID to delete") @PathVariable("accountId") Integer accountId);
}
