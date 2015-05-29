package co.com.home.utilities.enums;

/**
 * Enum que representa los diferentes comandos que se le pueden enviar al robot explorador
 * @author Daniel, danieltrujillo07@gmail.com
 */
public enum RobotCommandsEnum {

	/** Representa el girar 90 grados a la izquierda sin moverse */
	LEFT("I"),
	/** Representa el girar 90 grados a la derecha sin moverse */
	RIGTH("D"),
	/** Representa hacer un movimiento hacia adelante en la direccion que se encuentra el robot */
	ADVANCE("A");
	

	/**  Atributo para identificar cada uno de los enum */
	private String value;

	
	/** 
	 * Constructor por defecto del Enum
	 * @param value, valor a establecer al enum que se esta construyendo 
	 */
	private RobotCommandsEnum(String value){
		this.value = value;
	}

	
	/**
	 * Método encargado de encontrar el enum especifico para el value ingresado
	 * @param value valor a verificar
	 * @return Enum que cumple con el valor ingresado o null si no existe
	 */
	public static RobotCommandsEnum findOrientation(String value){
		if(value != null && !value.trim().isEmpty()){
			// se itera sobre los enum para identificar cual es el relacioado
			for (RobotCommandsEnum robotCommands : RobotCommandsEnum.values()) {
				if(robotCommands.getValue().equals(value)){
					return robotCommands;
				}
			}
		}
		return null;
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