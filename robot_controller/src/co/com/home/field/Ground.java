package co.com.home.field;

import java.util.List;

import co.com.home.position.Position;
import co.com.home.utilities.factories.PositionFactory;

/**
 * Clase que representa el terreno que se desea explorar
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class Ground {

	/** Atributo que representa el terreno en forma de grilla del terreno a explorar */
	private Position[][] field;


	/**
	 * Constructor por defecto de la clase
	 * @param superiorX coordenada mayor en X del terreno a explorar
	 * @param superiorY coordenada mayor en Y del terreno a explorar
	 */
	public Ground(int superiorX, int superiorY){
		super();
		// Se construye la grilla a modelar
		field = new Position[superiorX][superiorY];
	}
	
	/**
	 * Método encargado de construir cada una de las posiciones y si tiene amenaza, asociadas al terreno a explorar
	 * @param threats amenaza a establecer
	 */
	public void createField(List<String> threats){
		// Se construye la factory encargada de crear la posicion especifica
		PositionFactory instance = PositionFactory.getInstance();
		
	}

	/**
	 * get the value of the attribute field 
	 * @return the field value
	 */
	public Position[][] getField() {
		return field;
	}
	/**
	 * Set the value to the attribute field
	 * @param field the field to set
	 */
	public void setField(Position[][] field) {
		this.field = field;
	}
}