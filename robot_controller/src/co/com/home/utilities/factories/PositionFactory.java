package co.com.home.utilities.factories;

import co.com.home.position.concrete.FieldPosition;
import co.com.home.position.concrete.RobotPosition;

/**
 * Clase factory encargada de construir tipo de posicion concretas (Method Factory)
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class PositionFactory {

	/** Unica instancia de la clase factory */
	private static PositionFactory instance;
	
	
	/** Constructor por defecto de la clase */
	private PositionFactory(){
		super();
	}

	
	/**
	 * Método encargado de retornar un objeto concreto de tipo <code>RobotPosition<code>
	 * @return objeto concreto construido
	 */
	public RobotPosition RobotPosition(){
		return new RobotPosition();
	}
	/**
	 * Método encargado de retornar un objeto concreto de tipo <code>FieldPosition<code>
	 * @return objeto concreto construido
	 */
	public FieldPosition FieldPosition(){
		return new FieldPosition();
	}
	
	
	/**
	 * Método encargado de retornar la unica instancia de la clase factory
	 * @return unica instancia de la clase
	 */
	public static PositionFactory getInstance(){
		if(instance == null){
			instance = new PositionFactory();
		}
		return instance;
	}
}