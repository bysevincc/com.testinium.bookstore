package com.example.com.testinium.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Setter
@Getter
@Table(name = "bookstores")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookStore_id")
    private Long id;

    @Column(name = "bookStore_name")
    private String bookStoreName;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Book> books = new ArrayList<>();


}
