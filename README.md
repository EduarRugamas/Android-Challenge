# Android Challenge
<p align="center">Articulos Obtenidos mediante un API y Articulos creados localmente</p>

## Tabla de Contenidos
- [Descripción y contexto](#descripción-y-contexto)
- [Dependecias](#dependencias)
- [Autor/es](#autores)
- [Información adicional](#información-adicional)


## Descripción y contexto

_Creando un aplicacion donde se hace uso de la libreria retrofit2 para el consumo de API de articulos y uso de Base de datos firebase para la creacion de articulos locales_
_El proyecto esta pensado para consumir un api con la libreria retrofit2 obteniendo una data de articulos sobre android de manera que se muestre el titulo del
articulo de android, su contenido, una imagen de referencia, el autor que publico dicho articulo y la fecha de publicacion de este dicho articulo._

_la segunda funcionalidad del proyecto era conectar la aplicacion a una base de datos y poder alojar dentro de ella, informacion referente a un articulo creado por el usuario donde se solicitaba un titulo, un contenido, un autor, fecha de publicacion y una imagen de manera opcional de esta manera poder obtener todos estos datos guardados y poder realizar con esta informacion, poder editar dicho articulo y poder eliminar dicho articulo._
<h3 align="center">Funcionalidades</h3>

- Consumo de API con Retrofit
- Uso de corrutinas para el consumo de API
- Uso de Firebase Cloud Firestore para el almacenamiento de los articulos locales
- Editar articulos locales
- Eliminar articulos locales
- Uso de ViewBinding para el enlace entre la vista y su respectivo codigo
- Uso de lista dinamica RecyclerView


## Manual de usuario y Manual Tecnico
<h3 align="center" >Manuales</h3>

## Dependecias

<h3 align="center" >Dependencias utilizadas en el proyecto</h3>

- Retrofit2

```
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
```
- Gson Converte

```
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```
- Glide

```
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
```
- Corrutinas 

```
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1'
```
- Card View

```
   implementation "androidx.cardview:cardview:1.0.0"
```
- Firebase Cloud Firestore

```
   implementation 'com.google.firebase:firebase-firestore:23.0.1'
```
- RecyclerView

```
  implementation "androidx.recyclerview:recyclerview:1.2.1"
```

## Autor/es

* **Eduardo Rugamas** - *Desarrollador Android* - *Github* - *Email juaneduardo021299@hotmail.com* - [Eduar Rugamas](https://github.com/EduarRugamas)

## Información adicional

<h3 align="center" >Links de mockups</h3>

- link de Mockups creados en Figma
```
https://www.figma.com/file/SQbpRj7dUCvgsYq1TME3WI/android-challenge?node-id=0%3A1
```

<h3 align="center">Link Video Youtube</h3>
```
https://youtu.be/JX2S1TsvLjk
```

