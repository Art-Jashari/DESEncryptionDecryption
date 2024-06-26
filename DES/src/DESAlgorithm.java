import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DESAlgorithm {
    public static void main(String[] args) {
        System.out.println("Welcome to the DES program.");
        Scanner scanner = new Scanner(System.in);

        try {
            // Useri zgjedh dekriptimin apo enkriptimin.
            System.out.print("Doni të enkriptoni apo dekriptoni një mesazh? (enkripto/dekripto): ");
            String mode = scanner.nextLine().trim();

            // Kontrollollimi i inputit
            while (!mode.equalsIgnoreCase("enkripto") && !mode.equalsIgnoreCase("dekripto")) {
                System.out.print("Opsion i pavlefshëm. Ju lutemi shkruani 'enkripto' ose 'dekripto': ");
                mode = scanner.nextLine().trim();
            }

            // Kerko qelesin ( 8 bajt )
            System.out.print("Shkruani një çelës me gjatësi 8 bajtë: ");
            String keyString = scanner.nextLine();

            // Sigurohu që çelësi të jetë saktësisht 8 bajtë i gjatë
            while (keyString.getBytes().length != 8) {
                System.out.print("Çelës i pavlefshëm. Çelësi duhet të jetë saktësisht 8 bajtë i gjatë. Shkruani përsëri: ");
                keyString = scanner.nextLine();
            }

            byte[] keyBytes = keyString.getBytes();
            SecretKey key = new SecretKeySpec(keyBytes, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            if (mode.equalsIgnoreCase("enkripto")) {
                System.out.print("Doni të enkriptoni nga file apo ta shenoni mesazhin? (file/mesazh) ");
                String option = scanner.nextLine().trim();
                while (!option.equalsIgnoreCase("file") && !option.equalsIgnoreCase("mesazh")) {
                    System.out.print("Opsion i pavlefshëm. Ju lutemi shkruani 'mesazh' ose 'file': ");
                    option = scanner.nextLine().trim();
                }
                if (option.equalsIgnoreCase("file")) {
                    // Enkripto mesazhin
                    System.out.print("Shkruani path-in e file-it që dëshironi të enkriptoni: ");
                    String filePath = scanner.nextLine();
                    byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] encryptedBytes = cipher.doFinal(fileContent);
                    String encryptedHex = DatatypeConverter.printHexBinary(encryptedBytes);

                    // Saving encrypted message to file
                    try (FileOutputStream out = new FileOutputStream("mesazhi_enkriptuar.txt")) {
                        out.write(encryptedHex.getBytes());
                    }
                    System.out.println("Mesazhi i enkriptuar është ruajtur në 'mesazhi_enkriptuar.txt'.");
                }
                else {
                    System.out.print("Shkruani mesazhin që dëshironi të enkriptoni: ");
                    String message = scanner.nextLine();
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
                    String encryptedHex = DatatypeConverter.printHexBinary(encryptedBytes);
                    System.out.println("Mesazhi i Enkriptuar (Hex): " + encryptedHex);
                }
            } else if(mode.equalsIgnoreCase("dekripto")) {
                System.out.print("Doni të dekriptoni nga file apo ta shenoni mesazhin? (file/mesazh) ");
                String option = scanner.nextLine().trim();
                while (!option.equalsIgnoreCase("file") && !option.equalsIgnoreCase("mesazh")) {
                    System.out.print("Opsion i pavlefshëm. Ju lutemi shkruani 'mesazh' ose 'file': ");
                    option = scanner.nextLine().trim();
                }
                if (option.equalsIgnoreCase("file")) {
                    // Dekripto mesazhin
                    System.out.print("Shkruani path-in e file-it të enkriptuar (hex): ");
                    String filePath = scanner.nextLine();
                    String encryptedHex = new String(Files.readAllBytes(Paths.get(filePath)));
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] decryptedBytes = cipher.doFinal(DatatypeConverter.parseHexBinary(encryptedHex));
                    String decryptedMessage = new String(decryptedBytes, "UTF-8");

                    // Saving decrypted message to file
                    try (FileOutputStream out = new FileOutputStream("mesazhi_dekriptuar.txt")) {
                        out.write(decryptedMessage.getBytes());
                    }
                    System.out.println("Mesazhi i dekriptuar është ruajtur në 'mesazhi_dekriptuar.txt'.");
                }
                else {
                    System.out.print("Shkruani mesazhin e enkriptuar (hex): ");
                    String encryptedHex = scanner.nextLine();
                    try {
                        cipher.init(Cipher.DECRYPT_MODE, key);
                        byte[] decryptedBytes = cipher.doFinal(DatatypeConverter.parseHexBinary(encryptedHex));
                        String decryptedMessage = new String(decryptedBytes, "UTF-8");
                        System.out.println("Mesazhi i Dekriptuar: " + decryptedMessage);
                    } catch (javax.crypto.BadPaddingException | javax.crypto.IllegalBlockSizeException e) {
                        System.out.println("Qelsi per mesazhin e dhene nuk eshte i sakt!");
                    } catch (Exception e) {
                        System.err.println("Ndodhi një gabim gjatë dekriptimit: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Ndodhi një gabim: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
