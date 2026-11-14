# 📝 Rudimentary Word Processor — Java Swing Rich-Text Editor (`StyledEditorKit`)

[![Ask DeepWiki](https://img.shields.io/badge/Ask-DeepWiki-4B32C3?logo=read-the-docs&logoColor=white)](https://deepwiki.com/Drakonosinc/rudimentary-word-processor)
![Java](https://img.shields.io/badge/Java-SE_8%2B-ED8B00?logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing_StyledEditorKit-007396)
![License](https://img.shields.io/badge/License-MIT-green)

A desktop **Rich-Text Word Processor** built in **Java SE** using `javax.swing.JTextPane` and `javax.swing.text.StyledEditorKit`. It adopts the host operating system's native Look & Feel and provides real-time typography styling, paragraph alignment, color picker integration, context menus, keyboard accelerators, and live cursor telemetry.

---

## ✨ Key Features

- **Rich Typography Controls (`StyledEditorKit`)**:
  - Inline character styles: **Bold (`B`)**, *Italic (`I`)*, and <u>Underline (`S`)</u>.
  - Dynamic **System Font Family Selector** (`GraphicsEnvironment.getAvailableFontFamilyNames()`).
  - **Font Size Selector** ranging from `8 pt` to `72 pt`.
  - **Interactive Color Picker** powered by `JColorChooser`.
- **Paragraph Alignment**:
  - One-click toolbar actions for **Left (`◀`)**, **Center (`■`)**, **Right (`▶`)**, and **Justified (`◀▶`)** paragraph alignment.
- **Clipboard & Context Menu**:
  - Native Cut, Copy, and Paste actions accessible from the top menu bar, keyboard shortcuts, and right-click `JPopupMenu`.
- **Live Caret & Document Telemetry**:
  - Real-time bottom status bar tracking **Line**, **Column** (`Utilities.getRowStart`), and total **Character Count** via `CaretListener`.

---

## ⌨️ Keyboard Shortcuts

| Shortcut | Action |
| :--- | :--- |
| `Ctrl + N` | New Document menu item |
| `Ctrl + A` | Open Document menu item |
| `Ctrl + G` | Save Document menu item |
| `Ctrl + Q` | Exit Application |
| `Ctrl + Z` / `Ctrl + Y` | Undo / Redo menu items |
| `Ctrl + X` / `Ctrl + C` / `Ctrl + V` | Cut / Copy / Paste selection |

---

## 🏗️ Architecture

```text
rudimentary-word-processor/
├── ProcesadorTextoMejorado.java
│   ├── ProcesadorTextoMejorado   # Entry point (SwingUtilities.invokeLater + System LookAndFeel)
│   ├── EditorPrincipal           # Main JFrame (800x600) + JMenuBar & KeyStroke accelerators
│   └── EditorPanel               # BorderLayout workspace: JToolBar, JTextPane, JPopupMenu & CaretListener
├── .gitignore
└── README.md
```

---

## 🚀 Compilation & Execution

### Prerequisites
- **Java Development Kit (JDK) 8 or newer**

### Compile and Run
```bash
javac -encoding UTF-8 ProcesadorTextoMejorado.java
java ProcesadorTextoMejorado
```
