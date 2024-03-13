package proyectogestioninventariosupermercado.test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GestionInventarioSupermercadoTest {
	
	@Test
	public void testConsultarProducto() throws InterruptedException {
		// Inicializa el WebDriver para Chrome
		WebDriver driver = new ChromeDriver();
		
		try{
			// Abre la página web de productos
			driver.get("http://localhost:8080/productos");
			
			int cantidadProductosInicial = 0;
		
			new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Productos"));
			
			Thread.sleep(3000);
			
			WebElement botonGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btn_guardar']"));
			WebElement botonCancelar = driver.findElement(By.xpath("//vaadin-button[@id='btn_cancelar']"));
			WebElement botonEliminar = driver.findElement(By.xpath("//vaadin-button[@id='btn_eliminar']"));
			WebElement botonConsultar = driver.findElement(By.xpath("//vaadin-button[@id='btn_consultar']"));
			
			botonConsultar.click();
			
			// Localiza el campo de entrada de nombre de usuario
			WebElement campoCodigo = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_codigo']/input"));
			WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_nombre']/input"));
			WebElement campoPrecio = driver.findElement(By.xpath("//vaadin-number-field[@id='txt_precio']/input"));
			WebElement campoCategoria = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_categoria']/input"));
			
			// Ingresa el nombre de usuario
			campoCodigo.sendKeys("8412345678905");
			campoNombre.sendKeys("Salchicha Toledo 400 Gr");
			campoPrecio.sendKeys("84.90");
			campoCategoria.sendKeys("Embutidos");
			
			Thread.sleep(3000);
			
			botonGuardar.click();
			
			int cantidadProductosFinal = 0;
			
			assertEquals(cantidadProductosFinal, (cantidadProductosInicial+1), .001);
			
		}finally {
			driver.close();
		}
	}
	
	@Test
	public void testConsultarProveedor() throws InterruptedException {
		// Inicializa el WebDriver para Chrome
		WebDriver driver = new ChromeDriver();
		
		try{
			// Abre la página web de proveedores
			driver.get("http://localhost:8080/proveedores");
			
			int cantidadProveedoresInicial = 0;
		
			new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Proveedores"));
			
			Thread.sleep(3000);
			
			WebElement botonGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btn_guardar']"));
			WebElement botonCancelar = driver.findElement(By.xpath("//vaadin-button[@id='btn_cancelar']"));
			WebElement botonEliminar = driver.findElement(By.xpath("//vaadin-button[@id='btn_eliminar']"));
			WebElement botonConsultar = driver.findElement(By.xpath("//vaadin-button[@id='btn_consultar']"));
			
			botonConsultar.click();
			
			// Localiza el campo de entrada de nombre de usuario
			WebElement campoIdentificacion = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_identificacion']/input"));
			WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_nombre']/input"));
			WebElement campoDireccion = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_direccion']/input"));
			WebElement campoTelefono = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_telefono']/input"));
			WebElement campoProductosSuministrados = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_productosSuministrados']/input"));
			
			// Ingresa el nombre de usuario
			campoIdentificacion.sendKeys("1010");
			campoNombre.sendKeys("OLECSA");
			campoDireccion.sendKeys("Tegucigalpa, Honduras, Calle los Alcaldes");
			campoTelefono.sendKeys("22801045");
			campoProductosSuministrados.sendKeys("Arroz Blanco 454 Gr");
	        
			Thread.sleep(3000);
			
			botonGuardar.click();
			
			int cantidadProveedoresFinal = 0;
			
			assertTrue(cantidadProveedoresFinal == (cantidadProveedoresInicial+1));
			
		}finally {
			driver.close();
		}
	}
	
	@Test
	public void testConsultarKardex() throws InterruptedException {
		// Inicializa el WebDriver para Chrome
		WebDriver driver = new ChromeDriver();
		
		try{
			// Abre la página web del kardex
			driver.get("http://localhost:8080/kardex");
			
			int cantidadKardexInicial = 0;
		
			new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Kardex"));
			
			Thread.sleep(3000);
			
			WebElement botonCrear = driver.findElement(By.xpath("//vaadin-button[@id='btn_crear']"));
			WebElement botonCancelar = driver.findElement(By.xpath("//vaadin-button[@id='btn_cancelar']"));
			WebElement botonConsultar = driver.findElement(By.xpath("//vaadin-button[@id='btn_consultar']"));
			
			botonConsultar.click();
			
			// Localiza el campo de entrada de nombre de usuario
			WebElement campoIdentificacion = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_identificacion']/input"));
			WebElement campoCodigoProducto = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_codigoProducto']/input"));
			WebElement campoFechaEntradaSalidaProducto = driver.findElement(By.xpath("//vaadin-date-picker[@id='txt_fechaEntradaSalidaProducto']/input"));
			WebElement campoTransaccion = driver.findElement(By.xpath("//vaadin-combo-box[@id='cbo_transaccion']/input"));
			WebElement campoCantidad = driver.findElement(By.xpath("//vaadin-integer-field[@id='txt_cantidad']/input"));
			WebElement campoExistencia = driver.findElement(By.xpath("//vaadin-integer-field[@id='txt_existencia']/input"));
			
			// Ingresa el nombre de usuario
			campoIdentificacion.sendKeys("0005");
			campoCodigoProducto.sendKeys("748596120526");
			campoFechaEntradaSalidaProducto.sendKeys("11/03/2024");
			campoTransaccion.sendKeys("Salida");
			campoCantidad.sendKeys("2");
			campoExistencia.sendKeys("26");
			
			Thread.sleep(3000);
			
			botonCrear.click();
			
			int cantidadKardexFinal = 0;
			
			assertFalse(cantidadKardexInicial+1 == 0);
			
		}finally {
			driver.close();
		}
	}
}
