package com.basaki.model;

import lombok.Getter;
import lombok.Setter;

/**
 * {@code Book} represents a book entity.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/17/17
 */
@Getter
@Setter
public class Book {

    private Integer id;

    private String title;

    private String author;
}