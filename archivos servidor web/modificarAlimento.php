<?php
    include 'conexion.php';

    $nombre = $_POST['nombre'];
    $precio = $_POST['precio'];
    $idCategoria = $_POST['Categoria_idCategoria'];

    $sql = "Update Alimento set precio = '".$precio."', 
        Categoria_idCategoria = '".$idCategoria."' where nombre = '".$nombre."';";

    mysqli_query($conexion, $sql) or die (mysqli_error());
    mysqli_close($conexion);
?>