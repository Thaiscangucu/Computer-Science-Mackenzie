/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreavl;

/**
 *
 * @author USER
 */
public class No {
    int valor;
    No esquerda;
    No direita;
    int altura;  
    
    public No (int valor) {
        this.valor = valor;
        altura = 0;
    }
    public No() {}
    
}