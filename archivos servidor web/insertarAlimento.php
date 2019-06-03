<?php 
    include 'conexion.php';

    $nombre = $_POST['nombre'];
    $precio = $_POST['precio'];
    $idCategoria = $_POST['Categoria_idCategoria'];
    
    $sql = "insert into Alimento (nombre, precio, 
        Categoria_idCategoria) values ('".$nombre."', ".$precio.", ".$idCategoria.");";

    mysqli_query($conexion, $sql);
    mysqli_close($conexion);

?>