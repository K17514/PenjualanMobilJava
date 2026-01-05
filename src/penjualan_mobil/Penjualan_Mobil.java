/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_mobil;

/**
 *
 * @author USER
 */
public class Penjualan_Mobil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Koneksi k = new Koneksi();
        k.connect();
        
            new LamanLogin().show();
    }
    
}
