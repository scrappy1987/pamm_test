package net.atos.pamm.domain;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

public class ServiceResult {

    public enum Status {SUCCESS, UNAUTHORIZED, OP_ERROR, SYS_ERROR, NOT_IMPLEMENTED };

    private final Status status;
    private final JsonNode result;

    public ServiceResult (JsonNode result) {
        this.result = result;
        this.status = Status.SUCCESS;
    }

    public ServiceResult (String message) {
        this.result = Json.toJson("{\"message\":\"" + message + "\"}");
        this.status = Status.SUCCESS;
    }

    public ServiceResult(Status status, JsonNode result) {
        this.result = result;
        this.status = status;
    }

    public ServiceResult(Status status, String message) {
        this.result = Json.toJson("{\"message\":\"" + message + "\"}");
        this.status = status;
    }

    public ServiceResult(Status status) {
        this.result = null;
        this.status = status;
    }


    public Status getStatus() {
        return status;
    }

    public JsonNode getResult() {
        return result;
    }
}
