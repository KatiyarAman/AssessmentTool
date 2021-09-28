package com.sterp.multitenant.mastertenant.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client_verification_token")
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String token;

	@OneToOne(targetEntity = Clientage.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "client_id")
	private Clientage clientage;

	@Column(name = "expiry_date")
	private Date expiryDate;

	public VerificationToken() {
		// TODO Auto-generated constructor stub
	}

	public VerificationToken(String token, Clientage clientage) {
		super();
		this.token = token;
		this.clientage = clientage;
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Clientage getClientage() {
		return clientage;
	}

	public void setClientage(Clientage clientage) {
		this.clientage = clientage;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(int expiryTimeInMinutes) {
		this.expiryDate = this.calculateExpiryDate(EXPIRATION);
	}

}