<?php
    include 'conexion.php';

    $nombre = $_POST['nombre'];

    $sql = "delete from Alimento where nombre = '".$nombre."';";

    mysqli_query($conexion, $sql) or die(mysqli_error());
    mysqli_close($conexion);
?>