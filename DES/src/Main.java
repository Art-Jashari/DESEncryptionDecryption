import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    private static final String OUTPUT_FOLDER = "files_te_gjeneruar"; // Folderi ku ruhen të dhënat
    private static final String TEXT_FILE_PATH = OUTPUT_FOLDER + "/mesazhiFillestar.txt"; // File brenda folderit
    private static final String ENCRYPTED_FILE_PATH = OUTPUT_FOLDER + "/mesazhiEnkriptuar.txt"; // File brenda folderit
    private static final String DECRYPTED_FILE_PATH = OUTPUT_FOLDER + "/mesazhiDekriptuar.txt"; // File brenda folderit
    private static final String KEY_FILE_PATH = OUTPUT_FOLDER + "/celesi.txt"; // File brenda folderit

    private static Cipher cipher;
    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };

    public static void main(String[] args) {
        createOutputFolderIfNotExists(OUTPUT_FOLDER); // Siguron se folderi ekziston

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nShëno 'enkripto' për të enkriptuar një mesazh ose 'dekripto' për të dekriptuar një mesazh:");
        String operation = scanner.nextLine().toLowerCase();

        SecretKey secretKey = null;

        try {
            if (new File(KEY_FILE_PATH).exists()) {
                byte[] storedKey = readKeyFromFile(KEY_FILE_PATH);
                secretKey = new SecretKeySpec(storedKey, "DES");
            } else {
                System.out.println("Shtypni një çelës me 8 karaktere ose shkruani 'gjenero' për të krijuar një çelës të ri:");
                while (secretKey == null) {
                    String userInput = scanner.nextLine();

                    if (userInput.equalsIgnoreCase("gjenero")) {
                        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
                        keyGen.init(56);
                        secretKey = keyGen.generateKey();
                        saveKeyToFile(KEY_FILE_PATH, secretKey.getEncoded());
                    } else if (userInput.length() == 8) {
                        secretKey = new SecretKeySpec(userInput.getBytes(), "DES");
                        saveKeyToFile(KEY_FILE_PATH, userInput.getBytes());
                    } else {
                        System.out.println("Gjatësia e gabuar e çelësit. Ju lutem shtypni një çelës me 8 karaktere ose shkruani 'gjenero':");
                    }
                }
            }

            AlgorithmParameterSpec aps = new IvParameterSpec(initialization_vector);

            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            if (operation.equals("enkripto")) {
                if (!new File(TEXT_FILE_PATH).exists()) {
                    System.out.println("File-i nuk u gjet. Ju lutem shkruani tekstin për të krijuar një file të ri për enkriptim:");
                    String userText = scanner.nextLine();
                    createTextFile(TEXT_FILE_PATH, userText);
                }

                cipher.init(Cipher.ENCRYPT_MODE, secretKey, aps);
                encryption(new FileInputStream(TEXT_FILE_PATH), new FileOutputStream(ENCRYPTED_FILE_PATH));
                System.out.println("File-i është enkriptuar me sukses.");
            } else if (operation.equals("dekripto")) {
                if (!new File(ENCRYPTED_FILE_PATH).exists()) {
                    throw new IOException("File-i i enkriptuar nuk ekziston.");
                }

                cipher.init(Cipher.DECRYPT_MODE, secretKey, aps);
                decryption(new FileInputStream(ENCRYPTED_FILE_PATH), new FileOutputStream(DECRYPTED_FILE_PATH));
                System.out.println("File-i është dekriptuar me sukses.");
            } else {
                System.out.println("Hyrje jo valide. Ju lutem shtypni 'enkripto' ose 'dekripto'.");
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void createOutputFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Krijo një folder nëse nuk ekziston.
        }
    }

    private static void encryption(InputStream input, OutputStream output) throws IOException {
        output = new CipherOutputStream(output, cipher);
        writeBytes(input, output);
    }

    private static void decryption(InputStream input, OutputStream output) throws IOException {
        input = new CipherInputStream(input, cipher);
        writeBytes(input, output);
    }

    private static void writeBytes(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[512];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) >= 0) {
            output.write(buffer, 0, bytesRead);
        }
        input.close();
        output.close();
    }

    private static void createTextFile(String textFilePath, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(textFilePath)) {
            fos.write(content.getBytes());
        }
    }

    private static void saveKeyToFile(String filePath, byte[] key) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(key);
        }
    }

    private static byte[] readKeyFromFile(String filePath) throws IOException {
        byte[] key = new byte[8]; // DES-i përdor një çelës prej 8 byte.
        try (FileInputStream fis = new FileInputStream(filePath)) {
            fis.read(key);
        }
        return key;
    }
}
