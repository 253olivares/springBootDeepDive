package com.miguel.database_sql.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    private String isbn;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}
