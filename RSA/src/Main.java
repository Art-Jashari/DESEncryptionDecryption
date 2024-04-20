import javax.crypto.Cipher;
// Importo klasën Cipher nga paketa javax.crypto.
// Klasa Cipher ofron funksionalitetin për kodimin dhe dekodimin duke përdorur algoritme kriptografike të ndryshme.


import java.security.KeyPair;
// Importo klasën KeyPair nga paketa java.security.
// Klasa KeyPair paraqet një çift celesh kriptografik, zakonisht duke përfshirë një çelës publik dhe një çelës privat përkatës.

import java.security.KeyPairGenerator;
// Importo klasën KeyPairGenerator nga paketa java.security.
// Klasa KeyPairGenerator përdoret për të gjeneruar çifte të çelësave publik dhe privat për një algoritmë të caktuar.

import java.security.PrivateKey;
// Importo ndërfaqen PrivateKey nga paketa java.security.
// Ndërfaqja PrivateKey paraqet një çelës privat, i cili zakonisht përdoret për operacione dekodimi ose nënshkrimi.

import java.security.PublicKey;
// Importo ndërfaqen PublicKey nga paketa java.security.
// Ndërfaqja PublicKey paraqet një çelës publik, i cili zakonisht përdoret për operacione kodimi ose verifikimi të nënshkrimit.

import java.util.Base64;
// Importo klasën Base64 nga paketa java.util.
// Klasa Base64 ofron metoda statike për kodimin dhe dekodimin e të dhënave duke përdorur shkemën koduese Base64.
// Në këtë program, përdoret për të koduar mesazhin e koduar në një string të shkruar për qëllime shfaqje.


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the RSA program.");
        // test
    }
}