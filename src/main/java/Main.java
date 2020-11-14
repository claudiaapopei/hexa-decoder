import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter hexadecimal numbers:");
        Scanner scanner = new Scanner(System.in);
        String[] bytes = scanner.nextLine().split(" ");
        for (String s : bytes) {
            byte currentByte = Integer.decode("0x" + s).byteValue();
            byte incrementedByte = (byte) (currentByte + 1);
            System.out.printf("%02x", incrementedByte);
            System.out.print(" ");
        }
    }
}
