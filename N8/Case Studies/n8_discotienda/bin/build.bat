@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogota - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n
REM Todos los derechos reservados 2005
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2
REM Ejercicio: n7_discotienda
REM Autor: Nicol�s L�pez - 06/12/2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creaci�n de los directorios classes y lib
REM ---------------------------------------------------------

cd ..
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compila las clases del directotio source
REM ---------------------------------------------------------
cd source
javac -target 1.4 -source 1.4 -nowarn -d ../classes/ uniandes/cupi2/discotienda/mundo/*.java
javac -target 1.4 -source 1.4 -nowarn -d ../classes/ uniandes/cupi2/discotienda/interfaz/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ..
cd classes
jar cf ../lib/discotienda.jar uniandes/*

cd ../bin

pause
