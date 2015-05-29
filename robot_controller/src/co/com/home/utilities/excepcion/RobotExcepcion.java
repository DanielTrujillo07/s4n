package co.com.home.utilities.excepcion;

/**
 * Clase encargada de controlar y transportar las posibles exceciones que le sucedan al robot
 * @author Daniel, danieltrujillo07@gmail.com
 */
@SuppressWarnings("serial")
public class RobotExcepcion extends Exception{

	/**
	 * Constructor por defecto de la clase
	 * @param mensaje mensaje del error a transportar
	 */
	public RobotExcepcion(String mensaje){
		super(mensaje);
	}

}