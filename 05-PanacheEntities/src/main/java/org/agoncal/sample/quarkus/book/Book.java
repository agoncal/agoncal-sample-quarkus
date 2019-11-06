/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.agoncal.sample.quarkus.book;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends PanacheEntity {

    public String title;
    public String description;
    public Float price;
    public String isbn;
    public Integer nbOfPages;

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public Book price(Float price) {
        this.price = price;
        return this;
    }

    public Book description(String description) {
        this.description = description;
        return this;
    }

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Book nbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
        return this;
    }
}
