package com.frapaego.movie.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PrePersist;

public class MovieForm implements Serializable {
	private Long id;

	private String title;

	private String daoyan;

	private String jianjie;

	private Float rate;

	private Date releaseyear;

	private Date sendtime;

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

	public String getDaoyan() {
		return daoyan;
	}

	public void setDaoyan(final String daoyan) {
		this.daoyan = daoyan;
	}

	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(final String jianjie) {
		this.jianjie = jianjie;
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