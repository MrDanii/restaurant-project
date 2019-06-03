<?php
    include 'conexion.php';
    
    $nombre = $_POST['nombre'];
    $sql = "Insert INTO Categoria (nombre) values ('".$nombre."');";

    mysqli_query($conexion, $sql) or die (mysqli_error());
    mysqli_close($conexion);
?>