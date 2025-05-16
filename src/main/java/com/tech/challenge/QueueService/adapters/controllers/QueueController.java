package com.tech.challenge.QueueService.adapters.controllers;

import com.tech.challenge.QueueService.adapters.infra.impl.QueueRepository;
import com.tech.challenge.QueueService.core.domain.entities.Queue;
import com.tech.challenge.QueueService.core.domain.useCases.CreateQueueUseCase.ICreateQueueUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.FindNotFinishedItemsUseCase.IFindNotFinishedItemsUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.FindQueueByIdUseCase.IFindQueueByIdUseCase;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.DTOs.UpdateQueueUseCaseRequest;
import com.tech.challenge.QueueService.core.domain.useCases.UpdateQueueUseCase.IUpdateQueueUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController {

    @Autowired
    private IFindQueueByIdUseCase findQueueByIdUseCase;

    @Autowired
    private IFindNotFinishedItemsUseCase findNotFinishedItemsUseCase;

    @Autowired
    private IUpdateQueueUseCase updateQueueUseCase;

    @Autowired
    private ICreateQueueUseCase createQueueUseCase;

    @GetMapping("/queue/{queueId}")
    @Operation(summary = "Finds Queue Record from Id", description = "This endpoint is used to find queue record from Id", tags = {
            "Queue" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized Access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity findQueueById(@PathVariable Integer queueId){

        try {
            Queue queue = findQueueByIdUseCase.execute(queueId);
            return ResponseEntity.status(HttpStatus.OK).body(queue);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/queue")
    @Operation(summary = "Finds all not finished queue orders", description = "This endpoint is used to find all queue not finished", tags = {
            "Queue" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized Access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public List<Queue> findAllPendingQueue(){ return  this.findNotFinishedItemsUseCase.execute();}

    @PatchMapping("/queue/{queueItemId}")
    @Operation(summary = "Update Queue record status", description = "This endpoint is used to update an item queue", tags = {
            "Queue" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Queue.class))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized Access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity updateQueue(@PathVariable Integer queueId, @RequestBody Queue queue){

        try{
            UpdateQueueUseCaseRequest queueRequest = new UpdateQueueUseCaseRequest(queueId,queue.getStatus());
            Queue updatedQueue = updateQueueUseCase.execute(queueRequest);
            return ResponseEntity.status(HttpStatus.OK).body(updatedQueue);

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @PostMapping("/queue")
    @Operation(summary = "Create Queue record", description = "This endpoint is used to create an item queue", tags = {
            "Queue" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Queue.class))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized Access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity createQueue(@RequestBody Queue queue){

        try{
            Queue createdQueue = createQueueUseCase.execute(queue);
            return ResponseEntity.status(HttpStatus.OK).body(createdQueue);

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

}
