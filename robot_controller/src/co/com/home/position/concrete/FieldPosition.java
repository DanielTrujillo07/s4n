package co.com.home.position.concrete;

import co.com.home.position.Position;
import co.com.home.utilities.constants.SpecialCaracteresConstants;
import co.com.home.utilities.enums.ThreatTypeEnum;

/**
 * Clase concreta encargada de representar una posicion dentro del campo a explorar
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class FieldPosition extends Position{

	/** Atributo para identificar el tipo de amenaza que se presenta en esta posicion */
	public ThreatTypeEnum threatType;

	
	/**
	 * Metodo encargado de retornar las cordenadas de la posicion de forma especifica para la amenazas
	 * @return Cadena que representa la coordenada de la posicion 
	 */
	public String getPosition(){
		StringBuilder cadena = new StringBuilder();
		cadena.append(SpecialCaracteresConstants.PARENTESIS_IZQ);
		cadena.append(getPositionX());
		cadena.append(SpecialCaracteresConstants.COMA_SIMPLE);
		cadena.append(getPositionY());
		cadena.append(SpecialCaracteresConstants.PARENTESIS_DER);
		return cadena.toString();
	}
	
	
	/**
	 * get the value of the attribute threatType 
	 * @return the threatType value
	 */
	public ThreatTypeEnum getThreatType() {
		return threatType;
	}
	/**
	 * Set the value to the attribute threatType
	 * @param threatType the threatType to set
	 */
	public void setThreatType(String threatType) {
		this.threatType = ThreatTypeEnum.findThreatType(threatType);
	}
}