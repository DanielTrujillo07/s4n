package co.com.home.position;

import java.math.BigDecimal;

import co.com.home.utilities.constants.SpecialCaracteresConstants;

/**
 * Clase que representa una posicion dentro del terreno a explorar
 * @author Daniel, danieltrujillo07@gmail.com
 */
public abstract class Position {

	/** Atributo para identificar la posicion en X de la posicion */
	private Integer positionX = BigDecimal.ZERO.intValue();
	/** Atributo para identificar la posicion en Y de la posicion */
	private Integer positionY = BigDecimal.ZERO.intValue();

	/**
	 * Método encargado de identificar si el objeto pasado como parametro tiene exactamente las mismas
	 * </br> posiones que el actual si es sí dice que son iguales
	 * @param position objeto a verificar
	 * @return true si los objetos tienen las mismas coordenadas o false en caso contrario
	 */
	public Boolean equals(Position position){
		Boolean resultado = Boolean.FALSE;
		if(position != null && 
				this.getPositionX().equals(position.getPositionX()) && 
				this.getPositionY().equals(position.getPositionY())){
			resultado = Boolean.TRUE;
		}
		return resultado;
	}
	/** Método encargado de retornar la coordenada de la posicion */
	public String getPosition(){
		StringBuilder cadena = new StringBuilder();
		cadena.append(getPositionX());
		cadena.append(SpecialCaracteresConstants.CADENA_VACIA);
		cadena.append(getPositionY());
		return cadena.toString();
	}


	/**
	 * get the value of the attribute positionX 
	 * @return the positionX value
	 */
	public Integer getPositionX() {
		return positionX;
	}
	/**
	 * Set the value to the attribute positionX
	 * @param positionX the positionX to set
	 */
	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}
	/**
	 * get the value of the attribute positionY 
	 * @return the positionY value
	 */
	public Integer getPositionY() {
		return positionY;
	}
	/**
	 * Set the value to the attribute positionY
	 * @param positionY the positionY to set
	 */
	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}
}