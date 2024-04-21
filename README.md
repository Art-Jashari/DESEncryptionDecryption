# File Encryption and Decryption Utility

## University Details
- **University**: University of Prishtina "Hasan Prishtina"
- **Faculty**: Faculty of Electrical Engineering
- **Department**: Software Engineering Class
- **Course**: Data Security 
- **Semester**: 4th Semester

## Project Title
File and String Encryption/Decryption using DES Algorithm using Java

## Contributors
- [Art Kelmendi](<https://github.com/artkelmendi>)
- [Art Jashari](<https://github.com/Art-Jashari>)
- [Artin Rexhepi](<https://github.com/artin-rexhepi>)
- [Armenije Sadikaj](<link-to-github-profile>)

## Supervisor
- **Professor**: Mergim Hoti

## Project Description
This Java-based project provides a utility for encrypting and decrypting files and strings using the DES encryption algorithm. The application allows users to input the path of a file or directly enter a string to be encrypted or decrypted. This utility can be useful for ensuring data security in digital communications.

### Configuration and Setup
To run this project on your device, you will need:
- Java Development Kit (JDK) - Version 8 or later.
- An IDE such as IntelliJ IDEA or Eclipse to open and run the project.
- Download the necessary `jaxb-api` JAR file from [JAXB API Maven Repository](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api/2.4.0-b180830.0359).

#### Adding JAXB API to IntelliJ IDEA
1. Open your project in IntelliJ IDEA.
2. Navigate to **File > Project Structure**.
3. Select **Modules** under the Project Settings on the left.
4. Click on the **Dependencies** tab.
5. Click the '+' button at the bottom of the window to add a new dependency.
6. Choose **'JARs or directories...'**.
7. Find and select the downloaded JAR files in the file chooser dialog. You can select multiple JAR files at once by holding the Ctrl key (Cmd on macOS) while clicking.
8. Click **OK** to add the JARs to your module's dependencies.
9. Ensure that the JARs are marked as **'Compile'** in the drop-down menu to the right of the dependency entry so that they are included in the build path and available during compilation and runtime.
10. Click **OK** to close the Project Structure dialog.

### How to Run
1. Clone the repository or download the Java files to your local machine.
2. Open the terminal or command prompt.
3. Navigate to the directory containing the Java files.
4. Compile the Java program using `javac Main.java`.
5. Run the program using `java Main`.
6. Follow the on-screen prompts to either encrypt or decrypt a file or string.

## Project Results
The encryption and decryption tool:
- Allows users to securely encrypt files and strings, converting them into a non-readable format.
- Provides functionality to decrypt previously encrypted files and strings, restoring them to their original format.
- Users can expect the tool to generate encrypted outputs in hexadecimal format and to save these outputs into `.txt` files for encrypted data and `.txt` files for decrypted data.

This project not only serves an educational purpose by illustrating practical applications of encryption algorithms in data security but also provides a functional tool that can be used in real-world data protection scenarios.
