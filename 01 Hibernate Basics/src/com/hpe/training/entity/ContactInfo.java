package com.hpe.training.entity;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

// by itself, this class is not mapped to any table
// but, this can be part of another entity class (composition)
@Embeddable
@Getter
@Setter
public class ContactInfo {
	private String address;
	private String city;
	private String state;
	private String country;
	private String phone;
}
