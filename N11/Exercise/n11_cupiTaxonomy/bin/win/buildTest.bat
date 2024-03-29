@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad of los Andes (Bogot� - Colombia)
REM Departamento of Ingenier�a of Sistemas y Computaci�n
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n11_cupiTaxonomia
REM Autor: Catalina Rodr�guez - 11-abr-2012
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creaci�n of los directorios classes y lib en test
REM ---------------------------------------------------------

cd ../../test/
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compila las clases of the directotio test/source
REM ---------------------------------------------------------

cd source
javac -source 1.5 -classpath ../../lib/cupiTaxonomia.jar;../lib/junit.jar -d ../classes/ uniandes/cupi2/cupiTaxonomia/test/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir of los archivos compilados
REM ---------------------------------------------------------

cd ../classes

jar cf ../lib/cupiTaxonomiaTest.jar uniandes/* -C ../data .

cd ../../bin/win

pause