#  Java Notepad (Swing Desktop Application)

##  Overview

This project is a **desktop-based Notepad application** built using **Java Swing**.
It replicates core functionalities of a traditional text editor with additional enhancements like **dark theme UI, font customization, and status tracking**.

---

##  Features

### File Operations

* Create new document
* Open `.txt` files
* Save files
* Print documents

---

###  Editing Features

* Cut, Copy, Paste
* Select All
* Keyboard shortcuts support

---

###  Formatting Options

* Change font family
* Change font style (Plain, Bold, Italic)
* Adjust font size
* Word wrap toggle

---

###  View Options

* Toggle status bar visibility

---

###  Status Bar

Displays:

* Line number (Ln)
* Column number (Col)
* Character count
* Encoding format (UTF-8)
* Line ending format (CRLF)

---

###  UI Features

* Dark theme interface
* Custom caret color
* Smooth text editing experience

---

##  Technologies Used

* Java (Core)
* Java Swing (GUI)
* AWT (Event Handling)
* File I/O (BufferedReader, BufferedWriter)
* Event Listeners

---

##  Project Structure

```id="c4nq6o"
project-root/
│
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           ├── TextNotepad.java
│           ├── AbstractInheritanceDemo.java
│
├── target/
│   ├── classes/
│   │   └── *.class files
│   └── test-classes/
```

---

##  How to Run

### Using Java (Manual)

```bash id="8x6b8p"
javac TextNotepad.java
java TextNotepad
```

---

### Using Maven

```bash id="xtt6nr"
mvn compile
mvn exec:java
```

---

##  Keyboard Shortcuts

| Action     | Shortcut |
| ---------- | -------- |
| New File   | Ctrl + N |
| Open File  | Ctrl + O |
| Save File  | Ctrl + S |
| Print      | Ctrl + P |
| Cut        | Ctrl + X |
| Copy       | Ctrl + C |
| Paste      | Ctrl + V |
| Select All | Ctrl + A |

---

##  Design Highlights

* Event-driven architecture using `ActionListener`
* Modular UI initialization (`initUI`)
* Clean separation of UI and functionality
* Dynamic font customization
* Real-time caret tracking

---

## Limitations

* Supports only `.txt` files
* No autosave feature
* No syntax highlighting
* No tab/multi-file support

---

##  Future Improvements

* Add syntax highlighting (Java, Python, etc.)
* Multiple tabs support
* Auto-save functionality
* Find & Replace feature
* Themes (Light/Dark toggle)
* Line numbering sidebar

---

##  Author

**Ayush**

---

