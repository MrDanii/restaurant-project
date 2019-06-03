<?php
    include '../conexion.php';
    
    $clave_empleado = $_GET['clave_empleado'];

    $sql = "select SUM(propina) as total_propina from Orden where Empleado_clave_empleado = '".$clave_empleado."';";
        
    $result = $conexion->query($sql);

    while($fila = $result->fetch_array()){
        $empleados[] = array_map('utf8_encode',$fila);
    }

    echo json_encode($empleados);
    $result->close();
?>