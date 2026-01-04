# HashCheckr - SHA-256 File Hash Verifier

HashCheckr is a lightweight Java CLI tool I built to compute and verify SHA-256 checksums for files. It helps confirm file integrity by comparing an expected hash with the actual hash of a file.

---

## ✅ What It Does

- Computes the SHA-256 hash of any file
- Compares it to an expected checksum
- Prints a clear verification report in the terminal

---

## 🧪 Usage

Compile:

```sh
javac HashCheck.java
```

Run:

```sh
java HashCheck <filePath> <expectedSha256>
```

Example:

```sh
java HashCheck ./build.zip e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
```

---

## 📦 Project Structure

```text
HashCheckr/
├── HashCheck.java          # Main CLI program
└── README.md               # Project documentation
```

---

## 🧠 What I Learned

- Reading files efficiently with Java NIO streams
- Hashing data with `MessageDigest`
- Building a simple CLI tool with validation and exit codes

---

## 🛠️ Technologies Used

- Java
- SHA-256 (`MessageDigest`)

---

## 📜 License

This project is open source and available under the MIT License.
