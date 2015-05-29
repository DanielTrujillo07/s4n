package co.com.home.utilities.resources;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Clase utilitaria encargada de cerrar varios tipos de recursos del sistema
 * @author Daniel, danieltrujillo07@gmail.com
 */
public class CloseSystemResourceUtils {

	/** Unica instancia de la clase */
	private static CloseSystemResourceUtils instance;


	/** Constructor por defecto de la clase utilitaria */
	private CloseSystemResourceUtils(){
		super();
	}
	
	
	/**
	 * Metodo encargado de cerrar los recuros de tipo InputStream
	 * @param inputStream recurso a cerrar
	 */
	public void closeResource(InputStream inputStream){
		try {
			if(inputStream != null){
				inputStream.close();
			}
		} catch (Exception e) {
		}
	}
	/**
	 * Metodo encargado de cerrar los recuros de tipo OutputStream
	 * @param outputStream recurso a cerrar
	 */
	public void closeResource(OutputStream outputStream){
		try {
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
		}
	}
	/**
	 * Metodo encargado de cerrar los recuros de tipo Reader
	 * @param reader recurso a cerrar
	 */
	public void closeResource(Reader reader){
		try {
			if(reader != null){
				reader.close();
			}
		} catch (Exception e) {
		}
	}
	/**
	 * Metodo encargado de cerrar los recuros de tipo Writer
	 * @param writer recurso a cerrar
	 */
	public void closeResource(Writer writer){
		try {
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
		}
	}


	/**
	 * Método encargado de retornar la unica instancia de esta clase
	 * @return instancia de la clase
	 */
	public static CloseSystemResourceUtils getInstance() {
		if(instance == null){
			instance = new CloseSystemResourceUtils();
		}
		return instance;
	}
}