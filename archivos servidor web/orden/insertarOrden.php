<?php
    include '../conexion.php';

    $numero_mesa = $_POST['numero_mesa'];

    $sql = "insert into Orden (numero_mesa) values
        (".$numero_mesa."');";

    mysqli_query($conexion, $sql) or die (mysqli_error());
    mysqli_close($conexion);
?>