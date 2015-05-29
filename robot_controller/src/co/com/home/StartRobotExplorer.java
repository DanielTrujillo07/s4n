package co.com.home;

import java.util.List;

import co.com.home.field.Ground;
import co.com.home.robot.Robot;
import co.com.home.utilities.constants.SpecialCaracteresConstants;
import co.com.home.utilities.excepcion.RobotExcepcion;
import co.com.home.utilities.resources.LoadSystemResourcesUtils;


/**
 * Clase main del proyecto, sirve para realizar la prueba sobre el proyecto
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class StartRobotExplorer {

	/**
	 * Método main
	 * @param args parametros para iniciar el programa de prueba
	 */
	public static void main(String[] args){
		// Se leen los archivos con las entradas
		List<String> entradasRobot = LoadSystemResourcesUtils.getInstance().loadFileAsList("resources//datosEntradaRobot.txt");
		List<String> entradasAmenazas = LoadSystemResourcesUtils.getInstance().loadFileAsList("resources//datosEntradaAmenazas.txt");
		
		// Se verifica que se haya cargado informacion
		if(entradasRobot != null && !entradasRobot.isEmpty()){
			// Se organiza las entradas segun la descripcion del problema
			String entradaCoordenadaSuperiorTerreno = entradasRobot.remove(0);
			// Primero se construye el terreno a explorar y configura sus amenazas
			Ground ground = new Ground(Integer.valueOf(entradaCoordenadaSuperiorTerreno.split(SpecialCaracteresConstants.CADENA_VACIA.trim())[0]),
									   Integer.valueOf(entradaCoordenadaSuperiorTerreno.split(SpecialCaracteresConstants.CADENA_VACIA.trim())[2]));
			if(entradasAmenazas != null && !entradasAmenazas.isEmpty()){
				for (String amenaza : entradasAmenazas) {
					// Se normaliza la informacion encontrada
					amenaza = amenaza.replace(SpecialCaracteresConstants.PARENTESIS_IZQ, SpecialCaracteresConstants.CADENA_VACIA.trim()).replace(
											  SpecialCaracteresConstants.PARENTESIS_DER, SpecialCaracteresConstants.COMA_SIMPLE);
					ground.createThreat(Integer.valueOf(amenaza.split(SpecialCaracteresConstants.COMA_SIMPLE)[0]), Integer.valueOf(amenaza.split(SpecialCaracteresConstants.COMA_SIMPLE)[1]), amenaza.split(SpecialCaracteresConstants.COMA_SIMPLE)[2]);
				}
			}
			
			// Ahora se itera sobre la informacion de entrada del robot cada 2 registros
			for(int i = 0; i < entradasRobot.size(); i+=2){
				String entradaRobotPosicionInicial = entradasRobot.get(i);
				String entradaRoboComandos = entradasRobot.get(i+1);
				// Se construye un nuevo robot para cada linea de informacion que se recupere
				Robot robot = new Robot(Integer.valueOf(entradaRobotPosicionInicial.split(SpecialCaracteresConstants.CADENA_VACIA.trim())[0]), 
										Integer.valueOf(entradaRobotPosicionInicial.split(SpecialCaracteresConstants.CADENA_VACIA.trim())[2]), 
										entradaRobotPosicionInicial.split(SpecialCaracteresConstants.CADENA_VACIA.trim())[4], ground);
				try {
					// se le manda cada una de los comandos que debe ejecutar
					for (char command : entradaRoboComandos.toCharArray()) {
						robot.move(String.valueOf(command));
					}
				} catch (RobotExcepcion e) {
					// Se notifica lo que sucedio
					System.out.println(e.getMessage());
				}catch (Exception e) {
					// Se notifica lo que sucedio
					System.out.println(e.getMessage());
				}
				// finaliza la tarea del robot
				robot.completeExploration();
			}
		}
	}
}