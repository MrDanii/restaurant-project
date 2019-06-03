<?php
    include 'conexion.php';
    
    $sql = "Select * from Alimento order by Categoria_idCategoria asc;";
    $result = $conexion->query($sql);

    while($fila = $result->fetch_array()){
        $alimentos[] = array_map('utf8_encode', $fila);
    }

    echo json_encode($alimentos);
    $result->close();

?>