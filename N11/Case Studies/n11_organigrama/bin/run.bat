@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Todos los derechos reservados 2005
REM
REM Proyecto Cupi2
REM Ejercicio: n11_organigrama
REM Autor: Jorge Villalobos - 08-nov.-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion del programa
REM ---------------------------------------------------------

cd..
java -classpath ./lib/organigrama.jar;./lib/jcalendar.jar uniandes.cupi2.organigrama.interfaz.InterfazOrganigrama
cd bin