<?php
    include 'conexion.php';

    $nombre = $_GET['nombre'];
    
    $sql = "Select * from Alimento where Alimento.nombre = '".$nombre."'";
    $result = $conexion->query($sql);

    while($fila = $result->fetch_array()){
        $alimentos[] = array_map('utf8_encode', $fila);
    }

    echo json_encode($alimentos);
    $result->close();

?>