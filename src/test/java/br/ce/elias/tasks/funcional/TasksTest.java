package br.ce.elias.tasks.funcional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class TasksTest {

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		driver.findElement(By.id("saveButton")).click();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		String mensagem=driver.findElement(By.id("message")).getText();
		System.out.println("Mensagem!!!!=>"+mensagem);
		Assert.assertEquals("Sucess!", mensagem);
		driver.quit();
	}
	@Test
	public void deveSalvarTarefaComSucessoSeleniumGrid() throws MalformedURLException {
		
		
		WebDriver driver = acessarAplicacao();
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste via selenium remoto");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2031");
		
		driver.findElement(By.id("saveButton")).click();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		String mensagem=driver.findElement(By.id("message")).getText();
		System.out.println("Mensagem!!!!=>"+mensagem);
		Assert.assertEquals("Sucess!", mensagem);
		driver.quit();
	}
	public WebDriver acessarAplicacao() throws MalformedURLException {
		String iplocal = "http://192.168.0.13";
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL(iplocal+":4444/wd/hub"), cap);
		driver.navigate().to(iplocal+":8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
