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