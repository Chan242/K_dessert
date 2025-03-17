package admin.tag;

public class TagDto {
	int tagIndexInt = 0;
	String TagNameStr = "";
	
	
	
	public TagDto(int tagIndexInt, String tagNameStr) {
		super();
		this.tagIndexInt = tagIndexInt;
		TagNameStr = tagNameStr;
	}
	public int getTagIndexInt() {
		return tagIndexInt;
	}
	public void setTagIndexInt(int tagIndexInt) {
		this.tagIndexInt = tagIndexInt;
	}
	public String getTagNameStr() {
		return TagNameStr;
	}
	public void setTagNameStr(String tagNameStr) {
		TagNameStr = tagNameStr;
	}
	@Override
	public String toString() {
		return "TagDto [tagIndexInt=" + tagIndexInt + ", TagNameStr=" + TagNameStr + "]";
	}
	
	
}
