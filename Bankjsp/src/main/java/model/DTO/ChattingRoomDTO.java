package model.DTO;

public class ChattingRoomDTO {
	private int c_room;
	private String name;
	public ChattingRoomDTO(int c_room, String name) {
		super();
		this.c_room = c_room;
		this.name = name;
	}
	public ChattingRoomDTO() {
		super();
	}
	@Override
	public String toString() {
		return "ChattingRoomDTO [c_room=" + c_room + ", name=" + name + "]";
	}
	public int getC_room() {
		return c_room;
	}
	public void setC_room(int c_room) {
		this.c_room = c_room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
