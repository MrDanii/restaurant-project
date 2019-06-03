<?php
    include '../conexion.php';
    
    $cantidad = $_POST['cantidad'];
    $Alimento_idAlimento = $_POST['Alimento_idAlimento'];
    $Orden_idOrden = $_POST['Orden_idOrden'];
    
    $sql = "Insert into DetalleOrden (cantidad, Alimento_idAlimento, Orden_idOrden) values
        (".$cantidad.", ".$Alimento_idAlimento.", ".$Orden_idOrden.");";

    mysqli_query($conexion, $sql) or die (mysqli_error());
    mysqli_close($conexion);
?>