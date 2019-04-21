@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Todos los derechos reservados 2005
REM
REM Proyecto Cupi2
REM Ejercicio: n11_contactos
REM Autor: Katalina Marcos - 3-nov-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/contactos.jar;../test/lib/contactosTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.contactos.test.ContactoTest
java -classpath ../lib/contactos.jar;../test/lib/contactosTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.contactos.test.DirectorioTest