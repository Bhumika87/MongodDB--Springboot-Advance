package com.MongoDb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Document(collection="todos")
    public class TodoDTO {
        @Id
        private String id;
        @NotNull(message="Todo cannot be null")

        private String todo;
        @NotNull(message="description cannot be null")
        private String description;
        @NotNull(message="completed cannot be null")
        private boolean completed;
        private Date createdDate;
        private Date updatedDate;
}
