@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Todos los derechos reservados 2005
REM
REM Proyecto Cupi2
REM Ejercicio: n11_organigrama
REM Autor: Jorge Villalobos - 08-nov.-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ..
java -classpath ./lib/organigrama.jar;./test/lib/organigramaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.organigrama.test.EmpresaTest
java -classpath ./lib/organigrama.jar;./test/lib/organigramaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.organigrama.test.CargoTest
cd bin