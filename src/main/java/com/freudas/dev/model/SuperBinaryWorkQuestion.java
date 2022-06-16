package com.freudas.dev.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "superBinaryId")
public class SuperBinaryWorkQuestion extends WorkQuestion {

}
