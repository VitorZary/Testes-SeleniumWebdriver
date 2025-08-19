package br.com.vitorzary.GerenciadorTarefas;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Automatizados da aplicação Gerenciador de Tarefas")
public class GerenciadorDeTarefasTests {

    public WebDriver setarOWebDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        return navegador;
    }

    public WebDriver adicionarNovaTarefaNoGerenciadorDeTarefas(){
        WebDriver navegador = setarOWebDriver();
        navegador.get("https://react-para-iniciantes.vercel.app/");
        navegador.findElement(By.id("tituloTarefa")).sendKeys("Dentista");
        navegador.findElement(By.id("descricaoTarefa")).sendKeys("Ir ao escritório do dentista Fulano as 15:00");
        navegador.findElement(By.id("botaoAdicionar")).click();

        return navegador;
    }

    @Test
    @DisplayName("Adicionar nova tarefa no gerenciador de tarefas e verificar")
    public void testAdicionarNovaTarefaNoGerenciadorDeTarefasEVerificar() {
        WebDriver navegador = adicionarNovaTarefaNoGerenciadorDeTarefas();

        navegador.findElement(By.xpath("//ul/li[last()]//button[2]")).click();
        String tarefaTitulo = navegador.findElement(By.id("detTarefaTitulo")).getText();
        String tarefaDescricao = navegador.findElement(By.id("detTarefaDescricao")).getText();

        Assertions.assertEquals("Dentista", tarefaTitulo);
        Assertions.assertEquals("Ir ao escritório do dentista Fulano as 15:00", tarefaDescricao);
        navegador.quit();
    }

    @Test
    @DisplayName("Apagar uma tarefa no gerenciador de tarefas e verificar")
    public void testApagarUmaTarefaNoGerenciadorDeTarefasEVerificar() {
        WebDriver navegador = adicionarNovaTarefaNoGerenciadorDeTarefas();

        navegador.findElement(By.xpath("//ul/li[last()]//button[3]")).click();
        Boolean elementosDaLista = navegador.findElements(By.xpath("//ul/li")).size() > 0;

        Assertions.assertFalse(elementosDaLista);
        navegador.quit();
    }

}
