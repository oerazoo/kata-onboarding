# Kata - Onboarding customers 

## Introducción
Este proyecto es una solución integral de **Onboarding Bancario** diseñada para gestionar los nuevos clientes y la apertura cuentas financieras.

---

## 📂 Estructura del Proyecto

En este repositorio encuentras:

### onboarding_services 
Contiene la API REST desarrollada con (Spring Boot 4 + Java 21)

### onboarding_app 
Contiene la aplicación web desarrollada con angular 17 

---

## 🚀 Ejecución Local

### 1. Backend (Gradle)
Asegúrate de tener instalada la JDK 21 o superior.

***Limpiar y generar el empaquetado (.jar)***
```bash 
.\gradlew build
```

***Ejecutar la aplicación***
```bash 
java -jar build/libs/onboarding-0.0.1-SNAPSHOT.jar
```

### 2. Frontend (npx)
***Instalar dependencias***
```bash
npm install
```

***Levantar servidor de desarrollo***
```bash
ng serve
```
---

## ☁️  Despliegue en nube

En el siguiente diagrama se representa la configuración básica para publicar este producto usando algunas utilidades ofrecidas en AWS:

![deploy-onboarding](https://github.com/user-attachments/assets/e2343357-317c-4b7f-9ef7-e6d13a81146c)



