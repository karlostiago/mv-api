package com.mv.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vinculo")
public class Vinculo implements Serializable {

	private static final long serialVersionUID = -2015462521461858417L;
	
	private Long id;
}
