# Polyglot Security Log Analyzer

A real-time anomaly detection system built with a microservices architecture using **Java (Spring Boot)** and **Python (FastAPI)**.

## 🚀 How it Works
1. **Java Heart**: A Spring Boot service that generates simulated server logs every 5 seconds using `@Scheduled` tasks.
2. **Python Brain**: A FastAPI service that receives logs via REST API and uses rule-based logic to categorize security threats.
3. **Live Dashboard**: A responsive HTML5/JavaScript frontend that polls the Java backend for the latest analyzed threats.

## 🛠️ Tech Stack
- **Backend**: Java 17, Spring Boot, Maven
- **Analysis Engine**: Python 3.12, FastAPI, Uvicorn
- **Frontend**: HTML5, CSS3 (Modern Dark Theme), JavaScript (Fetch API)

## 📈 Key Features
- **Cross-Language Integration**: Seamless JSON data exchange between Java and Python.
- **Automated Data Lifecycle**: Zero-manual input required for full system demonstration.
- **Visual Alerting**: Color-coded severity levels (Safe, Warning, Danger, Critical).