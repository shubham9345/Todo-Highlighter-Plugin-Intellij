# TodoHighlighter IntelliJ Plugin

A plugin for IntelliJ IDEA that automatically scans open Kotlin and Java files for `// TODO:` comments, highlights them inline, and provides a sidebar tool window to list and navigate to each TODO.

---

## ✨ Features

- **Inline TODO Highlighting**  
  - Scans `.kt` and `.java` files for `// TODO:` comments  
  - Highlights each TODO line directly in the editor

- **“TODOs” Sidebar Tool Window**  
  - Lists all TODO comments from the current file  
  - Click to navigate directly to the line in the editor

- **Live Updates**  
  - Real-time scanning as you edit the file  
  - Sidebar and highlights update instantly

- **Supports Kotlin and Java**  
  - Works seamlessly with `.kt` and `.java` files

- **Lightweight Implementation**  
  - Uses simple regex scanning  
  - Minimal impact on performance

---

## 📦 Installation

### 1. From JetBrains Marketplace (if published)

- Go to **File → Settings → Plugins**  
- Search for `TodoHighlighter`  
- Click **Install** and restart IntelliJ

### 2. Manual Installation (development build)

```bash
git clone https://github.com/yourusername/todohighlighter-plugin.git
cd todohighlighter-plugin
./gradlew buildPlugin
