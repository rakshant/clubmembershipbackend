package com.cmm.spring.entity;


public class HostingCount{
		String _id;
		long TotalFee;
		
		public HostingCount(String type, long price) {
			super();
			this._id = type;
			this.TotalFee = price;
		}
		public HostingCount() {
		}
		public String getType() {
			return _id;
		}
		public void setType(String type) {
			this._id = type;
		}
		public long getPrice() {
			return TotalFee;
		}
		public void setPrice(long price) {
			this.TotalFee = price;
		}
		@Override
		public String toString() {
			return "HostingCount [_id=" + _id + ", TotalFee=" + TotalFee + "]";
		}
		
}