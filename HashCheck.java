/**
 * HashCheck CLI
 *
 * Usage: java HashCheck <filePath> <expectedSha256>
 *
 * Computes SHA‑256 hash of the specified file and compares it
 * to the expected value. Prints whether verification succeeded
 * and logs details.
 *
 * Author: Adrian Calugarescu
 * Date: 2025‑05‑28
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashCheck {

    private final Path file;
    private final String expected;

    /**
     * Constructs a new verifier for the given file and expected checksum.
     * @param file      path to the file to hash
     * @param expected  expected SHA‑256 checksum (lower/upper‑case accepted)
     */
    public HashCheck(Path file, String expected) {
        this.file = file;
        this.expected = expected.toLowerCase();
    }

    /**
     * Computes the SHA‑256 hash and prints a verification report.
     * @return true if the actual hash matches the expected one
     */
    public boolean verify() throws IOException, NoSuchAlgorithmException {
        String actual = computeSha256();
        boolean match = actual.equals(expected);
        System.out.println("File     : " + file);
        System.out.println("Expected : " + expected);
        System.out.println("Actual   : " + actual);
        System.out.println(
            match ? "\u2714 Checksums match." : "\u2718 Checksums differ!"
        );
        return match;
    }

    /**
     * Streams the file through a MessageDigest to compute its SHA‑256.
     * @return lowercase hex representation of the digest
     */
    private String computeSha256()
        throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (var in = Files.newInputStream(file)) {
            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) > 0) {
                digest.update(buffer, 0, n);
            }
        }
        byte[] hashBytes = digest.digest();
        return HexFormat.of().formatHex(hashBytes);
    }

    private static void usage() {
        System.out.println("HashCheck - SHA-256 verifier");
        System.out.println("Usage: java HashCheck <filePath> <expectedSha256>");
    }

    /**
     * Entry‑point. Exits with non‑zero status on error.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            usage();
            System.exit(1);
        }
        try {
            Path file = Path.of(args[0]);
            String expected = args[1];
            if (!Files.exists(file)) {
                System.err.println("File not found: " + file);
                System.exit(2);
            }
            HashCheck checker = new HashCheck(file, expected);
            checker.verify();
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            System.exit(3);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 not supported: " + e.getMessage());
            System.exit(4);
        }
    }
}
