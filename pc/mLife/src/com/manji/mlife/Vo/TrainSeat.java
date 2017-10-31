package com.manji.mlife.Vo;

public class TrainSeat {

		private String seatName;
		private String seatId;
		private String seatPrice;
		private String remainderTrainTickets;
		public String getSeatName() {
			return seatName;
		}
		public void setSeatName(String seatName) {
			this.seatName = seatName;
		}
		public String getSeatId() {
			return seatId;
		}
		public void setSeatId(String seatId) {
			this.seatId = seatId;
		}
		public String getSeatPrice() {
			return seatPrice;
		}
		public void setSeatPrice(String seatPrice) {
			this.seatPrice = seatPrice;
		}
		public String getReminderTrainTickets() {
			return remainderTrainTickets;
		}
		public void setRemainderTrainTickets(String remainderTrainTickets) {
			this.remainderTrainTickets = remainderTrainTickets;
		}
		@Override
		public String toString() {
			return "TrainSeat [seatName=" + seatName + ", seatId=" + seatId + ", seatPrice=" + seatPrice
					+ ", reminderTrainTickets=" + remainderTrainTickets + "]";
		}
		
		
	
}
