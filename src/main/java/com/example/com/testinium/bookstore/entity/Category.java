package com.example.com.testinium.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Entity
@Setter
@Getter
@Builder
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Book> books = new ArrayList<>();
    public Category(){

    }


}
