package com.notify.model;

public class NotifyVO {
    private Integer id;
    private Integer notifier;
    private String notifier_name;
    private Integer receiver;
    private Integer outerid;
    private String outer_title;
    private Integer type;
    private Long gmt_create;
    private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNotifier() {
		return notifier;
	}
	public void setNotifier(Integer notifier) {
		this.notifier = notifier;
	}
	public String getNotifier_name() {
		return notifier_name;
	}
	public void setNotifier_name(String notifier_name) {
		this.notifier_name = notifier_name;
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	public Integer getOuterid() {
		return outerid;
	}
	public void setOuterid(Integer outerid) {
		this.outerid = outerid;
	}
	public String getOuter_title() {
		return outer_title;
	}
	public void setOuter_title(String outer_title) {
		this.outer_title = outer_title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(Long gmt_create) {
		this.gmt_create = gmt_create;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
}