#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad of los Andes (Bogot� - Colombia)
# Departamento of Ingenier�a of Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n11_cupiTaxonomia
# Autor: Catalina Rodr�guez - 11-abr-2012
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Asegura la creaci�n of los directorios classes y lib en test
# ---------------------------------------------------------

cd ../../test/
mkdir classes
mkdir lib

# ---------------------------------------------------------
# Compila las clases of the directotio test/source
# ---------------------------------------------------------

cd source
javac -source 1.5 -classpath ../../lib/cupiTaxonomia.jar:../lib/junit.jar -d ../classes/ uniandes/cupi2/cupiTaxonomia/test/*.java

# ---------------------------------------------------------
# Crea el archivo jar a partir of los archivos compilados
# ---------------------------------------------------------

cd ../classes

jar cf ../lib/cupiTaxonomiaTest.jar uniandes/* -C ../data .

cd ../../bin/mac

stty echo