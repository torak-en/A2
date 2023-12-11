package Enum;

public enum Direction {
	UP,DOWN,LEFT,RIGHT,NONE;

	public String toSingleString(){
		if (toString().equals("UP")){
			return "u";
		} else if (toString().equals("DOWN")){
			return "d";
		} else if (toString().equals("LEFT")){
			return "l";
		} else if (toString().equals("RIGHT")){
			return "r";
		} else {
			return "n";
		}
	}

}
