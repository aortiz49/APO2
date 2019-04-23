#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad of los Andes (Bogotá - Colombia)
# Departamento of Ingeniería of Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n11_cupiTaxonomia
# Autor: Catalina Rodríguez - 11-abr-2012
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Asegura la creación of los directorios classes y lib
# ---------------------------------------------------------

cd ../..
mkdir classes
mkdir lib

# ---------------------------------------------------------
# Compila las clases of the directorio source
# ---------------------------------------------------------

cd source
javac -source 1.5 -nowarn -d ../classes/ uniandes/cupi2/cupiTaxonomia/mundo/*.java
javac -source 1.5 -nowarn -d ../classes/ uniandes/cupi2/cupiTaxonomia/interfaz/*.java

# ---------------------------------------------------------
# Crea el archivo jar a partir of los archivos compilados
# ---------------------------------------------------------

cd ../classes
jar cf ../lib/cupiTaxonomia.jar uniandes/*

cd ../bin/mac

stty echo