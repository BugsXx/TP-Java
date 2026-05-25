<img width="1249" height="682" alt="image" src="https://github.com/user-attachments/assets/c0f1b4de-9875-46c5-a8f6-69139c277f85" />

# 🎓 Sistema de Gestión Académica (TPJAVA)

Este proyecto simula la gestión de alumnos, asignaturas y el control de presentismo de una institución educativa. Su función principal es **calcular automáticamente la condición final del alumno** (Promociona, Habilita o Libre) según sus asistencias acumuladas y su tipo de inscripción.

---

## 🏗️ Estructura del Proyecto

El código está organizado en dos paquetes principales:
* **`TPJAVA.domain`**: Contiene toda la lógica interna, las reglas de negocio, herencia y los objetos de datos.
* **`TPJAVA.gui`**: Se encarga de la interfaz visual y la interacción con el usuario.

### 🗺️ Mapa de Clases (Arquitectura)

[ Universidad ] ──> Tiene muchas ──> [ Asignatura ]
│
Es heredada por
│
▼
[ Clase ] (Control diario)
│
Registra asistencia en
│
▼
[ Inscripcion ] (Abstracta)
/       |

/        |

[Regular]     [Condicional]   [Oyente]

---

## 📂 Clases del Dominio (`TPJAVA.domain`)

### 🏫 `Universidad.java`
* **Función:** Es el contenedor raíz del sistema. Se encarga de administrar y almacenar el catálogo global de materias de la institución.
* **Método clave:** `agregarAsignatura(...)` para instanciar e insertar nuevas cátedras en la lista interna.

### 👤 `Alumno.java`
* **Función:** Modela al estudiante dentro del sistema. Almacena su `matricula` y una lista de las materias en las que se encuentra inscripto.
* **Particularidad:** Sobrescribe el método `equals` para comparar alumnos por número de matrícula, evitando duplicados en memoria.

### 📚 `Asignatura.java`
* **Función:** Representa una materia (con su código, nombre y total de clases). Contiene la lista de todas las inscripciones asociadas.
* **Optimización:** El método `cargaAsistencia()` utiliza un **`Iterator`** para recorrer la `LinkedList` de manera eficiente uno a uno, eliminando el problema de rendimiento del `.get(i)` tradicional y previniendo errores si la lista está vacía.

### 📆 `Clase.java`
* **Función:** Hereda de `Asignatura` y representa el dictado de la materia en un día y horario específico.
* **Método clave:** `tomaAsistencia(Alumno)`. Registra al alumno en la lista de presentes del día e impacta de forma automática en el acumulador general de la materia llamando a `cargaAsistencia()`.

---

## 📊 Reglas de Asistencia (Polimorfismo)

Cada tipo de inscripción sobreescribe de forma diferente los métodos abstractos `Promociona()` y `Habilita()`. La clase base calcula la situación final del alumno en el método común `Condicion()`.

### 📐 Matriz de Requisitos para Alumnos

| Tipo de Inscripción | Tipo de Materia | % Asistencia para Promocionar | % Asistencia para Habilitar |
| :--- | :--- | :--- | :--- |
| 🟢 **Regular** | 🛠️ Práctica (`'P'`) | Mayor o igual al 60% | Mayor o igual al 50% |
| 🟢 **Regular** | 📘 Obligatoria (`'O'`) | Mayor o igual al 80% | Mayor o igual al 60% |
| 🟢 **Regular** | ⚙️ Otras | No permite promoción | Mayor o igual al 75% |
| 🟡 **Condicional** | 🛠️ Práctica (`'P'`) | Mayor o igual al 80% | Mayor o igual al 70% |
| 🟡 **Condicional** | 📘 Obligatoria (`'O'`) | 100% (Asistencia Perfecta) | Mayor o igual al 80% |
| 🟡 **Condicional** | ⚙️ Otras | No permite promoción | Mayor o igual al 95% |
| 🔴 **Oyente** | 🌍 Cualquiera | Nunca (False) | Nunca (False) |

> 💡 **Nota técnica:** Las operaciones matemáticas utilizan un casteo a `(double)` antes de dividir para evitar que Java trunque los valores decimales de los porcentajes.

---

## 🚀 Interfaz y Ejecución

* **`Main.java`**: Punto de entrada del programa (`main`). Inicia el flujo de la aplicación delegando el control al entorno gráfico/consola.
* **`Menu.java`** (`TPJAVA.gui`): Contiene el método estático `abreMenu()` que gestiona las opciones visibles para que el usuario interactúe con el dominio.
