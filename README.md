# Investment Visualization Tool

A Java desktop application that visualizes investment performance using bar charts. The tool calculates invested vs. current values for various assets based on user input and renders them dynamically using the JFreeChart library.

## 📊 Features

- Compare original investment vs. current market value using side-by-side bar charts
- Dynamically parses user input (metal name, amount, purchase price, and spot price)
- Uses JFreeChart for visual rendering and Swing for the GUI
- Handles malformed input with robust exception checking

## 🛠 Technologies Used

- Java SE
- Swing (Java GUI)
- JFreeChart `1.0.19`
- JCommon `1.0.23`
- SQLite (via JDBC driver)

## 📦 Dependencies

Ensure the following `.jar` libraries are added to your classpath:

| Library | Path | Version |
|--------|------|---------|
| [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) | `/Users/jagrajgill/Desktop/CODE/Java` | 3.50.1.0 |
| [jfreechart](https://sourceforge.net/projects/jfreechart/) | `/Downloads/jfreechart-1.0.19/lib` | 1.0.19 |
| [jcommon](https://sourceforge.net/projects/jfreechart/) | `/Downloads/jfreechart-1.0.19/lib` | 1.0.23 |

> ⚠️ Ensure these JAR files are marked as "Compile" scope in your IDE (e.g., IntelliJ IDEA).

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/JagrajG/InvestmentTracker.git
   cd InvestmentTracker
