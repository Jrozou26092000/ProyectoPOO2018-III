/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Clase encargada de ejecutar las acciones del juego en un bucle. Asímismo, 
 * mustra la interfaz gráfica del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Arias
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class LoopJuego extends AnimationTimer implements Runnable{
    private Cronometro cronometro;
    private boolean cont;
    private Nivel nivel;
    private Scene escena; //Para controlar los eventos del teclado y para el cambio de nivel.
    private GraphicsContext lapiz; //Objeto con el que se va a "dibujar" en el canvas
    private Image Fondo; //Imagen de fondo
    
    /**
     * Constructor de la calse para inicializar los atributos.
     * @param escena 
     * @param lapiz 
     * @param Fondo Ruta de la imagen de fondo.
     * @param Campesino Ruta de la imagen del campesino.
     * @param Enemigo Ruta de la imagen de los enemigos.
     */
    public LoopJuego(Scene escena, GraphicsContext lapiz) throws IllegalArgumentException{
        this.escena = escena;
        this.lapiz = lapiz;
        this.nivel = new Nivel(escena,50, 50, "ImagenesJuego/Campesino_aba.png", 
                "ImagenesJuego/Enemigo_N1_aba.png", this.lapiz,"ImagenesJuego/Pared.png");
        this.Fondo = new Image("ImagenesJuego/Fondo.png");
        //this.Enemigo = new Image(Enemigo);
        this.cronometro = new Cronometro();
        this.cont = true;
    }
    
    /**
     * COMPLETAR...
     * @param now 
     */
    @Override
    public void handle(long now) {
        //Se establece el fondo.
        lapiz.drawImage(this.Fondo, 0, 0 );
        
        //dibujar cronometro
        lapiz.setFill(Color.WHITE);
        lapiz.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        if(this.cont){
            cronometro.start();
            this.cont = false;
        }
        lapiz.fillText(cronometro.getCadena(),900, 40);
        
        
        /*Se implementa el siguiente fragmento de codigo para poder visulizar
        las formas de los elementos del juego y así poder detectar(visualmente)
        cuando halla una colisión.
        */
        /*for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if ((i==0)||(i==13)) {
                    Shape f = new Rectangle(50*j, 50*i, 50, 50);
                    lapiz.strokeRect(50*j, 50*i, 50, 50);
                }else if(i%2!=0){
                    Shape f = new Rectangle(50*j, 50*i, 50, 50);
                    lapiz.strokeRect(50*j, 50*i, 50, 50);
                }else if(i%2==0){
                    if (j%2==0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        lapiz.strokeRect(50*j, 50*i, 50, 50);
                    }else if (j%2!=0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        lapiz.strokeRect(50*j, 50*i, 50, 50);
                    }
                }
            }
        }*/
        
        this.nivel.dibujarParedes();
        run();
    }      
    
    @Override 
    public void run(){
        //Metodo para mover el campesiono de acuerdo a las distintas animaciones
        this.nivel.moverCampesino();
        //Metodos para reportar si hay colision entre el personaje y algun muro
        //Se detecctan las colisiones superior-inferior y laterales por separado.
        this.nivel.DetectarColisionLadosCampesino();
        this.nivel.DetectarColisionSuperiorCampesino();
        //Metodo para mover los cinco enemigos del juego.
        this.nivel.moverEnemigos(0);
        this.nivel.moverEnemigos(1);
        this.nivel.moverEnemigos(2);
        this.nivel.moverEnemigos(3);
        this.nivel.moverEnemigos(4);
        this.nivel.moverEnemigos(5);
        this.nivel.moverEnemigos(6);
        this.nivel.moverEnemigos(7);
        this.nivel.moverEnemigos(8);
        this.nivel.moverEnemigos(9);
        this.nivel.Semilla();
    }

    /**
     * Metodo que retorna el objeto cronometro.
     * @return 
     */
    public Cronometro getCronometro() {
        return cronometro;
    }
    
    /**
     * Metodo que cambia el valor del booleano cont.
     * @param cont 
     */
    public void setCont(boolean cont) {
        this.cont = cont;
    }

    
}
