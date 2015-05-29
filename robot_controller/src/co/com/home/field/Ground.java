package co.com.home.field;

import java.math.BigDecimal;

import co.com.home.position.Position;
import co.com.home.position.concrete.FieldPosition;
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
		field = new Position[superiorX+1][];
		for (int i = 0; i <  superiorX+1; i++) {
			field[i] = new Position[superiorY+1];
		}
		createField();
	}
	
	/**
	 * Método encargado de crear un tipo de amenaza especifico en una posicion dentro del terreno
	 * @param posX coordenada en x para la amenaza
	 * @param posY coordenada en y para la amenaza
	 * @param threat tipo de amenaza
	 */
	public void createThreat(Integer posX, Integer posY, String threat){
		// Se verifica 	que llega unas coordenadas validas dentro del terreno a explorar
		if(posX >= BigDecimal.ZERO.intValue() && posY >= BigDecimal.ZERO.intValue() && 
				posX < field.length && posY < field[posX].length){
			// se agrega la amenaza a la posicion ingresada
			((FieldPosition)field[posX][posY]).setThreatType(threat);
		}
	}

	/** Método encargado de construir cada una de las posiciones y si tiene amenaza, asociadas al terreno a explorar */
	private void createField(){
		// Se construye la factory encargada de crear la posicion especifica
		PositionFactory factory = PositionFactory.getInstance();
		for(int i = BigDecimal.ZERO.intValue(); i < field.length; i++){
			for(int j = BigDecimal.ZERO.intValue(); j < field[i].length; j++){
				Position pos = factory.FieldPosition();
				pos.setPositionX(i);
				pos.setPositionY(j);
				field[i][j] = pos;
			}
		}
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