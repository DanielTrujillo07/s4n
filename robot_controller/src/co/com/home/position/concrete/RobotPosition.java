package co.com.home.position.concrete;

import co.com.home.position.Position;
import co.com.home.utilities.constants.SpecialCaracteresConstants;
import co.com.home.utilities.enums.OrientationEnum;

/**
 * Clase concreta encargada de representar una posicion del robot dentro del terreno que esta explorando
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class RobotPosition extends Position{
	
	/** Atributo para identificar la orientacion en la posicion del robot */
	private OrientationEnum orientation;
	
	/** Método encargado de retornar las coordenadas de la posicion actual mas su orientacion */
	public String getPosition(){
		StringBuilder cadena = new StringBuilder(super.getPosition());
		cadena.append(SpecialCaracteresConstants.CADENA_VACIA);
		cadena.append(getOrientation().getValue());
		return cadena.toString();
	}

	/**
	 * get the value of the attribute orientation 
	 * @return the orientation value
	 */
	public OrientationEnum getOrientation() {
		return orientation;
	}
	/**
	 * Set the value to the attribute orientation
	 * @param orientation the orientation to set
	 */
	public void setOrientation(String orientation) {
		this.orientation = OrientationEnum.findOrientation(orientation);
	}
}