package com.hype.application.dto.auth;

public record MailResponseDTO (
        boolean found
) {
    public MailResponseDTO(boolean found) {
        this.found = found;
    }

}
