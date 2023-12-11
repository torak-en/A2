package Enum;

/**
 * Enum representing a number of 5 different directions in the game.
 */

public enum Direction {
	UP,DOWN,LEFT,RIGHT,NONE;

	/**
	 * Returns a single string representation of the direction enum, allowing for file comparisons.
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
