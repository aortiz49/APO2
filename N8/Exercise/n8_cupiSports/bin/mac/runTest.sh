#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n8_cupiSports
# Autor: Equipo Cupi2 2015
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecuci�n de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath ./lib/cupiSports.jar:./test/lib/cupiSportsTest.jar:./test/lib/junit.jar junit.swingui.TestRunner AthleteTest
java -ea -classpath ./lib/cupiSports.jar:./test/lib/cupiSportsTest.jar:./test/lib/junit.jar junit.swingui.TestRunner SportTest
java -ea -classpath ./lib/cupiSports.jar:./test/lib/cupiSportsTest.jar:./test/lib/junit.jar junit.swingui.TestRunner CupiSportsTest
cd bin/mac

stty echo
