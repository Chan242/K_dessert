package admin.order.status;

public class OrderStatusDto {
	int staIndexInt = 0;
	String staStatusStr = "";
	String staNoticeStr = "";
	public int getStaIndexInt() {
		return staIndexInt;
	}
	public void setStaIndexInt(int staIndexInt) {
		this.staIndexInt = staIndexInt;
	}
	public String getStaStatusStr() {
		return staStatusStr;
	}
	public void setStaStatusStr(String staStatusStr) {
		this.staStatusStr = staStatusStr;
	}
	public String getStaNoticeStr() {
		return staNoticeStr;
	}
	public void setStaNoticeStr(String staNoticeStr) {
		this.staNoticeStr = staNoticeStr;
	}
	@Override
	public String toString() {
		return "OrderStatusDto [staIndexInt=" + staIndexInt + ", staStatusStr=" + staStatusStr + ", staNoticeStr="
				+ staNoticeStr + "]";
	}
	
	

}
