package com.frapaego.movie.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "rate")
	private Float rate;

	@Column(name = "releaseyear")
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseyear;

	@Column(name = "sendtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendtime;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(final Float rate) {
		this.rate = rate;
	}

	public Date getReleaseyear() {
		return releaseyear;
	}

	public void setReleaseyear(final Date releaseyear) {
		this.releaseyear = releaseyear;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(final Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	@PrePersist
	public void prePersist() {
		this.createdDate = new Date();
	}

	@Override
	public String toString() {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Movie{" + "id=" + id + ", title=" + title + ", rate=" + rate + ", releaseyear="
				+ sdf.format(releaseyear) + ", sendtime=" + sdf.format(sendtime) + '}';
	}

}