package com.dev0kch.mybook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
public class Abonne extends Mobinaute{

    String abonner;
}
