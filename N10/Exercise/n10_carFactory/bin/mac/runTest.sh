#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n10_fabricaDeCarros
# Autor: Vanessa P�rez Romanello - 05-mar-2012
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecuci�n de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath lib/fabricaDeCarros.jar:test/lib/junit.jar:test/lib/fabricaDeCarrosTest.jar junit.swingui.TestRunner uniandes.cupi2.carFactory.test.FabricaDeCarrosTest
		
java -ea -classpath lib/fabricaDeCarros.jar:test/lib/junit.jar:test/lib/fabricaDeCarrosTest.jar junit.swingui.TestRunner uniandes.cupi2.carFactory.test.ParteTest
cd bin/mac

stty echo
