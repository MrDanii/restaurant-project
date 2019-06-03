<?php
    $hostname = 'localhost';
    $database = 'restaurantmovil';
    $username = 'root';
    $password = 'admin';

    $conexion = new mysqli($hostname,$username,$password,$database);

    if($conexion->connect_errno){
        echo "No hay conexión";
    }

?>