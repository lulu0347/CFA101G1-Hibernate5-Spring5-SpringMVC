package com.repairform.model;

import java.sql.Date;

public class RepairFormVO implements java.io.Serializable{
		
	//private static final long serialVersionUID = 1L;
	
		private Integer repairformno;            /*1.檢測維修編號(PK,AUTO_INCREMENT)*/
		private Integer memno;                    /*2.會員編號(FK)*/
		private Integer orderno;                   /*3.一般商城訂單編號(FK)*/
		private Integer itemno;                     /*4.商品編號(FK)*/
		private Date createtime;                /*5.表單建立時間*/
		private String repairformstatus;           /*6.維修狀態*/
		private Integer adminno;                   /*7.確認維修單員工編號(FK)*/
		private String repairinfo;                 /*8.修理備註*/
		private Date repairend;                 /*9.表單完成時間*/
		
		
		public Integer getRepairformno() {
			return repairformno;
		}
		public void setRepairformno(Integer repairformno) {
			this.repairformno = repairformno;
		}
		public Integer getMemno() {
			return memno;
		}
		public void setMemno(Integer memno) {
			this.memno = memno;
		}
		public Integer getOrderno() {
			return orderno;
		}
		public void setOrderno(Integer orderno) {
			this.orderno = orderno;
		}
		public Integer getItemno() {
			return itemno;
		}
		public void setItemno(Integer itemno) {
			this.itemno = itemno;
		}
		public Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}
		public String getRepairformstatus() {
			return repairformstatus;
		}
		public void setRepairformstatus(String repairformstatus) {
			this.repairformstatus = repairformstatus;
		}
		public Integer getAdminno() {
			return adminno;
		}
		public void setAdminno(Integer adminno) {
			this.adminno = adminno;
		}
		public String getRepairinfo() {
			return repairinfo;
		}
		public void setRepairinfo(String repairinfo) {
			this.repairinfo = repairinfo;
		}
		public Date getRepairend() {
			return repairend;
		}
		public void setRepairend(Date repairend) {
			this.repairend = repairend;
		}
}
		
		
		
		
