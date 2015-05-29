package co.com.home.robot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.com.home.field.Ground;
import co.com.home.position.Position;
import co.com.home.position.concrete.FieldPosition;
import co.com.home.position.concrete.RobotPosition;
import co.com.home.utilities.constants.MessagesConstants;
import co.com.home.utilities.constants.SpecialCaracteresConstants;
import co.com.home.utilities.enums.OrientationEnum;
import co.com.home.utilities.enums.RobotCommandsEnum;
import co.com.home.utilities.enums.ThreatTypeEnum;
import co.com.home.utilities.excepcion.RobotExcepcion;
import co.com.home.utilities.factories.PositionFactory;

/**
 * Clase que representa un robot explorador
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class Robot {

	/** Atributo para identificar la posicion actual del robot */
	private Position actualPosition;
	/** Lista de las amenazas encontradas en la exploracion del terreno */
	private List<Position> threatsFound;
	/** Lista de todo el recorrido del robot */
	private List<Position> oldPosition;
	/** Atributo que representa el terreno que se esta explorando*/
	private Ground ground;
	
	
	/**
	 * Constructor por defecto de la clase, para darle la ubicacion inicial al robot
	 * @param x coordenada en x de la posicion inicial del robot
	 * @param y coordenada en y de la posicion inicial del robot
	 * @param orientation orientacion inicial del robot
	 * @param ground terreno que esta explorando el robot
	 */
	public Robot(Integer x, Integer y, String orientation, Ground ground){
		super();
		setActualPosition(createPosition(x, y, orientation));
		setGround(ground);
		// ya que es la primer posicion, se verifica si tiene una amenaza
		findThreat(getActualPosition());
	}
	
	/** Metodo encargado de terminar la exploracion del robot sobre un terreno */
	public void completeExploration(){
		// primero se escribe la posicion final del robot
		System.out.println(((RobotPosition)getActualPosition()).getPosition());
		// se verifica si se encontraron amenazas y si es así se procede a escribirlas
		if(getThreatsFound() != null && !getThreatsFound().isEmpty()){
			StringBuilder mensaje = new StringBuilder(MessagesConstants.MENSAJE_AMENAZAS_DETECTADAS); 
			for (Position threat : getThreatsFound()) {
				mensaje.append(((FieldPosition)threat).getPosition());
				mensaje.append(SpecialCaracteresConstants.CADENA_VACIA);
			}
			System.out.println(mensaje.toString());
		}
	}

	/**
	 * Metodo encargado de movar el robot
	 * @param command orden para que el robot ejecute 
	 */
	public void move(String command) throws Exception{
		// se verifica que llegue el comando correcto
		if(command != null && !command.trim().isEmpty() && RobotCommandsEnum.findOrientation(command) != null){
			// Se recupera el comando a ejecutar 
			RobotCommandsEnum robotCommand = RobotCommandsEnum.findOrientation(command);
			Position nextPosition = 
					calculateNextPosition(robotCommand, getActualPosition().getPositionX(), getActualPosition().getPositionY(), ((RobotPosition)getActualPosition()).getOrientation());
			// se verifica si se pudo calcular la siguiente posicion
			if(nextPosition != null){
				// con la nueva informacion de la proxima posicion se verifica que efectivamente este dentro del terreno y 
				// si es así se busca amenasas ademas de moverse el robot
				boolean isPositionContent = validateFieldPosition(nextPosition);
				if(isPositionContent){
					// ya que todo resulto bien, se pasa la posicion actual a la lista de posiciones pasadas y se mueve a la nueva direccion
					getOldPosition().add(getActualPosition());
					setActualPosition(nextPosition);
				}else{
					// Se notifica que a la posicion donde se desea mover es una posicion invalida dentro del terreno que se esta explorando
					throw new RobotExcepcion(MessagesConstants.MENSAJE_POSICION_INVALIDA);
				}
			}
		}
	}
	
	/**
	 * Método encargado de identificar que la position a la cual se desea mover, efectivamente se encuentre dentro del terrono explorado
	 * @param nextPosition posicion a la cual se desea mover
	 * @return true si la validacion es exitosa, false en caso contrario
	 */
	private boolean validateFieldPosition(Position nextPosition) {
		// se verifica que las coordenadas de la posicion sean validas y se encuentren dentro del terreno
		if(nextPosition != null && getGround() != null && 
				nextPosition.getPositionX() >= BigDecimal.ZERO.intValue() && nextPosition.getPositionY() >= BigDecimal.ZERO.intValue() && 
				nextPosition.getPositionX() < getGround().getField().length && 
				nextPosition.getPositionY() < getGround().getField()[nextPosition.getPositionX()].length){
			// ya que es un campo valido, se procede a identificar posible amenaza
			findThreat(nextPosition);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Metodo encargado de identificar posibles amenazas en la position del terreno que se desea mover
	 * @param nextPosition posicion a la cual se va a mover el robot
	 */
	private void findThreat(Position nextPosition) {
		if(nextPosition != null && getGround() != null){
			Position field = getGround().getField()[nextPosition.getPositionX()][nextPosition.getPositionY()];
			// se identifica si el campo tiene una amenaza
			if(field instanceof FieldPosition && 
					((FieldPosition)field).getThreatType() != null && !((FieldPosition)field).getThreatType().equals(ThreatTypeEnum.NOTHING)){
				// ya que existe una amenaza se registra esta posicion si previamente no se había identificado
				boolean addThreat = Boolean.TRUE;
				for (Position pos : getThreatsFound()) {
					if(pos.equals(field)){
						addThreat = Boolean.FALSE;
						break;
					}
				}
				if(addThreat){
					getThreatsFound().add(field);
				}
			}
		}
	}

	/**
	 * Metodo encargado de calcular las coordenadas y la orientacion del robot segun el comando ingresado
	 * @param command comando a ejecutar
	 * @param x coordenada actual en x de la posicion del robot
	 * @param y coordenada actual en x de la posicion del robot
	 * @param orientation orientacion actual del robot
	 * @return posicion creada segun el comando y la informacion actual
	 */
	private Position calculateNextPosition(RobotCommandsEnum command, Integer x, Integer y, OrientationEnum orientation) {
		boolean isChange = Boolean.FALSE;
		// Se calcula la siguiente posicion segun la informacion ingresada
		switch (command) {
		// Se verifica si el comando es avanzar, si es así se calcula las nuevas coordenadas segun las actuales y la orientacion
		case ADVANCE:
			// se identifica cual es la orientacion actual
			if(orientation.equals(OrientationEnum.NORTH)){
				// ya que es norte se le suma uno a la cordenada en Y
				y++;
			}else{
				if(orientation.equals(OrientationEnum.EAST)){
					// ya que la orientacion es este se le suma 1 a la coordenada en X
					x++;
				}else{
					if(orientation.equals(OrientationEnum.SOUTH)){
						// ya que la orientacion es sur se le resta 1 a la coordenada Y
						y--;
					}else{
						// Se toma que la orientacion es oeste, por tanto se le resta 1 a la coordenada en X
						x--;
					}
				}
			}
			// se marca cambio en los datos
			isChange = Boolean.TRUE;
			break;		
			// Si se gira solo a la izquierda, solamente cambia la orientacion
		case LEFT:
			orientation = orientation.calculateOrientation(command);
			// se marca cambio en los datos
			isChange = Boolean.TRUE;
			break;
			// Si se gira solo a la izquierda, solamente cambia la orientacion
		case RIGTH:
			orientation = orientation.calculateOrientation(command);
			// se marca cambio en los datos
			isChange = Boolean.TRUE;
			break;
		default:
			// Por defecto no se realiza ninguna accion
			break;
		}
		// se construye y retorna la nueva posicion solo si se presento algun cambio
		if(isChange){
			// ya que se presento un cambio, se procede a crear la nueva posicion donde se movera el robot
			return createPosition(x, y, orientation.getValue());
		}
		return null;
	}

	/**
	 * Método encargado de crear una posicion relacionada al robot
	 * @param x coordenada en X
	 * @param y coordenada en y
	 * @param orientation orientacion del robot
	 * @return posicion del robot creada
	 */
	private Position createPosition(Integer x, Integer y, String orientation){
		// Se construye la factory encargada de crear la posicion especifica
		PositionFactory factory = PositionFactory.getInstance();
		Position pos = factory.RobotPosition();
		pos.setPositionX(x);
		pos.setPositionY(y);
		((RobotPosition)pos).setOrientation(orientation);
		return pos;
	}
	

	/**
	 * get the value of the attribute actualPosition 
	 * @return the actualPosition value
	 */
	public Position getActualPosition() {
		return actualPosition;
	}
	/**
	 * Set the value to the attribute actualPosition
	 * @param actualPosition the actualPosition to set
	 */
	public void setActualPosition(Position actualPosition) {
		this.actualPosition = actualPosition;
	}
	/**
	 * get the value of the attribute threatsFound 
	 * @return the threatsFound value
	 */
	public List<Position> getThreatsFound() {
		if(threatsFound == null){
			threatsFound = new ArrayList<Position>();
		}
		return threatsFound;
	}
	/**
	 * Set the value to the attribute threatsFound
	 * @param threatsFound the threatsFound to set
	 */
	public void setThreatsFound(List<Position> threatsFound) {
		this.threatsFound = threatsFound;
	}
	/**
	 * get the value of the attribute oldPosition 
	 * @return the oldPosition value
	 */
	public List<Position> getOldPosition() {
		if(oldPosition == null){
			oldPosition = new ArrayList<Position>();
		}
		return oldPosition;
	}
	/**
	 * Set the value to the attribute oldPosition
	 * @param oldPosition the oldPosition to set
	 */
	public void setOldPosition(List<Position> oldPosition) {
		this.oldPosition = oldPosition;
	}
	/**
	 * get the value of the attribute ground 
	 * @return the ground value
	 */
	public Ground getGround() {
		return ground;
	}
	/**
	 * Set the value to the attribute ground
	 * @param ground the ground to set
	 */
	public void setGround(Ground ground) {
		this.ground = ground;
	}
}