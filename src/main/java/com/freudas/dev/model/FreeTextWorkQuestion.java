package com.freudas.dev.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "freeTextId")
public class FreeTextWorkQuestion extends WorkQuestion{
}
