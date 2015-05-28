package co.com.home.utilities.enums;

/**
 * Enum que representa las diferentes amenazas que se pueden presentar en la exploracion de un terreno
 * @author Daniel, danieltrujillo07@gmail.com
 */
public enum ThreatTypeEnum {

	/** Representa sin Amenaza */
	NOTHING(null),
	/** Representa una mina antipersona */
	MINE("*"),
	/** Representa una mina Bomba */
	BOMB("%");

	
	/**  Atributo para identificar cada uno de los enum */
	private String value;


	/** 
	 * Constructor por defecto del Enum
	 * @param value, valor a establecer al enum que se esta construyendo 
	 */
	private ThreatTypeEnum(String value){
		this.value = value;
	}


	/**
	 * Método encargado de encontrar el enum especifico para el value ingresado
	 * @param value valor a verificar
	 * @return Enum que cumple con el valor ingresado o ThreatTypeEnum.NOTHING si no existe o no se ingreso parametro
	 */
	public static ThreatTypeEnum findThreatType(String value){
		if(value != null && !value.trim().isEmpty()){
			// se itera sobre los enum para identificar cual es el relacionado
			for (ThreatTypeEnum threatEnum : ThreatTypeEnum.values()) {
				if(threatEnum.getValue().equals(value)){
					return threatEnum;
				}
			}
		}
		return ThreatTypeEnum.NOTHING;
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