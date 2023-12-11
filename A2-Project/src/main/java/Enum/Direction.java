package Enum;

/**
 * The class representing a directions in the game.
 */

public enum Direction {
	UP,DOWN,LEFT,RIGHT,NONE;

	/**
	 * Returns a single string representation of the directions read.
	 * @return The respective direction.
	 */

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
