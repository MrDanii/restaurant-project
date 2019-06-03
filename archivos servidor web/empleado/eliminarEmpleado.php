<?php
    include '../conexion.php';

    $clave_empleado = $_POST['clave_empleado'];

    $sql = "delete from Empleado where clave_empleado = '".$clave_empleado."';";

    mysqli_query($conexion, $sql) or die(mysqli_error());
    mysqli_close($conexion);
?>