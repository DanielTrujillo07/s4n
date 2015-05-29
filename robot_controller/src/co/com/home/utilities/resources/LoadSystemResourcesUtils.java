package co.com.home.utilities.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase utilitaria para cargar recursos del sistema
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class LoadSystemResourcesUtils {

	/** Unica instancia de la clase */
	private static LoadSystemResourcesUtils instance;


	/** Constructor por defecto de la clase utilitaria */
	private LoadSystemResourcesUtils(){
		super();
	}

	
	/**
	 * Metodo encargado de leer un achivo y retornar su contenido en una lista de cadenas por lineas
	 * @param urlFile url del archivo a cargar
	 * @return lista con el contenido del archivo o vacia si existe algun problema
	 */
	public List<String> loadFileAsList(String urlFile){
		// Se identificar que llegue una url 
		if(urlFile != null && !urlFile.trim().isEmpty()){
			// Se construye el resultado de la lectura del archivo
			List<String> resultado = new ArrayList<String>();
			// Se definen todas los objetos necesarios para la lectura del archivo
			FileReader fileReader = null; 
			BufferedReader bufferedReader = null;
			try {
				// Se inicia la carga del archivo
				fileReader = new FileReader(urlFile);
				bufferedReader = new BufferedReader(fileReader);
				String line = null;
				while((line = bufferedReader.readLine()) != null) { 
					resultado.add(line);
				}
			} catch (Exception e) {
				resultado.add(e.getMessage());
			}finally{
				// Se cierran todos los recursos utilizados
				CloseSystemResourceUtils.getInstance().closeResource(bufferedReader);
				CloseSystemResourceUtils.getInstance().closeResource(fileReader);
			}
			// Se retorna el resultado del proceso
			return resultado;
		}
		return null;
	}

	/**
	 * Metodo encargado de leer un achivo y retornar su contenido en una cadena
	 * @param urlFile url del archivo a cargar
	 * @return cadena con el contenido del archivo o vacia si existe algun problema
	 */
	public String loadFileAsString(String urlFile){
		// Se identificar que llegue una url 
		if(urlFile != null && !urlFile.trim().isEmpty()){
			// Se construye el resultado de la lectura del archivo
			StringBuilder resultado = new StringBuilder();
			// Se definen todas los objetos necesarios para la lectura del archivo
			FileReader fileReader = null; 
			BufferedReader bufferedReader = null;
			try {
				// Se inicia la carga del archivo
				fileReader = new FileReader(urlFile);
				bufferedReader = new BufferedReader(fileReader);
				String line = null;
				while((line = bufferedReader.readLine()) != null) { 
					resultado.append(line);
					resultado.append("\n");
				}
			} catch (Exception e) {
				resultado.append(e.getMessage());
			}finally{
				// Se cierran todos los recursos utilizados
				CloseSystemResourceUtils.getInstance().closeResource(bufferedReader);
				CloseSystemResourceUtils.getInstance().closeResource(fileReader);
			}
			// Se retorna el resultado del proceso
			return resultado.toString();
		}
		return null;
	}


	/**
	 * Método encargado de retornar la unica instancia de esta clase
	 * @return instancia de la clase
	 */
	public static LoadSystemResourcesUtils getInstance() {
		if(instance == null){
			instance = new LoadSystemResourcesUtils();
		}
		return instance;
	}
}