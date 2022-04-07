package com.example.backend.dto;

import com.example.backend.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public enum SectionDto {;

    private interface Id { @PositiveOrZero Long getId(); }
    private interface Title { @NotBlank String getTitle(); }

    private interface Version { @PositiveOrZero int getVersion(); }
    private interface Created { @NotBlank LocalDateTime getCreated(); }
    private interface Modified { @NotBlank LocalDateTime getModified(); }
    private interface StatusI { @NotBlank Status getStatus(); }

    public enum Request {;
        @Getter @Setter @NoArgsConstructor
        public static class Create implements Title {

            private String title;
        }
    }

    public enum Response {;
        @Getter @Setter @NoArgsConstructor
        public static class Public implements Id, Title, Version, Created, Modified, StatusI {
            private Long id;
            private String title;

            private int version;
            private LocalDateTime created;
            private LocalDateTime modified;
            private Status status;
        }

        @Getter @Setter @NoArgsConstructor
        public static class Private implements Id, Title, Created, Modified, StatusI {
            private Long id;
            private String title;

            private int version;
            private LocalDateTime created;
            private LocalDateTime modified;
            private Status status;
        }
    }

}
