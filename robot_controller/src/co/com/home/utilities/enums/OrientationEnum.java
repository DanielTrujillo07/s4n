package co.com.home.utilities.enums;

/**
 * Enum que representa las diferentes orientaciones cardinales que puede tener una coordenada
 * @author Daniel, danieltrujillo07@gmail.com
 */
public enum OrientationEnum {

	/** Representa el norte */
	NORTH("N"),
	/**  Representa el Este */
	EAST("E"),
	/**  Representa el Sur */
	SOUTH("S"),
	/**  Representa el Oeste */
	WEST("W");

	
	/**  Atributo para identificar cada uno de los enum */
	private String value;

	
	/** 
	 * Constructor por defecto del Enum
	 * @param value, valor a establecer al enum que se esta construyendo 
	 */
	private OrientationEnum(String value){
		this.value = value;
	}

	
	/**
	 * Método encargado de encontrar el enum especifico para el value ingresado
	 * @param value valor a verificar
	 * @return Enum que cumple con el valor ingresado o null si no existe
	 */
	public static OrientationEnum findOrientation(String value){
		if(value != null && !value.trim().isEmpty()){
			// se itera sobre los enum para identificar cual es el relacioado
			for (OrientationEnum orientationEnum : OrientationEnum.values()) {
				if(orientationEnum.getValue().equals(value)){
					return orientationEnum;
				}
			}
		}
		return null;
	}

	
	/**
	 * Método encargado de calcular una orientacion segun la orientacion actual y el comando ingresado
	 * @param command comando para calcular orientacion
	 * @return nueva orientacion calculada
	 */
	public OrientationEnum calculateOrientation(RobotCommandsEnum command) {
		// Se hace el resultado a la orientacion actual
		OrientationEnum resultado = this;
		// Se verifica si la orientacion actual es NORTE
		if(this.equals(OrientationEnum.NORTH)){
			switch (command) {
			// Si se esta en norte y el giro es a la izquierda se retorna OESTE
			case LEFT:
				resultado = OrientationEnum.WEST;
				break;
				// Ya que el giro no es a la izquierda se toma por defecto a la derecha y estando al norte se retorna ESTE
			default:
				resultado = OrientationEnum.EAST;
				break;
			}
		}else{
			// Se verifica si la orientacion actual es ESTE
			if(this.equals(OrientationEnum.EAST)){
				switch (command) {
				// Si se esta en ESTE y el giro es a la izquierda se retorna NORTE
				case LEFT:
					resultado = OrientationEnum.NORTH;
					break;
					// Ya que el giro no es a la izquierda se toma por defecto a la derecha y estando al ESTE se retorna SUR
				default:
					resultado = OrientationEnum.SOUTH;
					break;
				}
			}else{
				// Se verifica si la orientacion actual es SUR
				if(this.equals(OrientationEnum.SOUTH)){
					switch (command) {
					// Si se esta en SUR y el giro es a la izquierda se retorna ESTE
					case LEFT:
						resultado = OrientationEnum.EAST;
						break;
						// Ya que el giro no es a la izquierda se toma por defecto a la derecha y estando al SUR se retorna OESTE
					default:
						resultado = OrientationEnum.WEST;
						break;
					}
				}else{
					// Se procede como si la orientacion fuera OESTE
					switch (command) {
					// Si se esta en OESTE y el giro es a la izquierda se retorna SUR
					case LEFT:
						resultado = OrientationEnum.SOUTH;
						break;
						// Ya que el giro no es a la izquierda se toma por defecto a la derecha y estando al OESTE se retorna NORTE
					default:
						resultado = OrientationEnum.NORTH;
						break;
					}
				}
			}
		}		
		return resultado;
	}
	
	
	/**
	 * get the value of the attribute value 
	 * @return the value value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * Set the value to the attribute value
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}