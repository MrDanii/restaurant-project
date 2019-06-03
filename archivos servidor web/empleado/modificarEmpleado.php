<?php
    include '../conexion.php';

    $clave_empleado = $_POST['clave_empleado'];
    $nombre = $_POST['nombre'];
    $apellido_p = $_POST['apellido_paterno'];
    $apellido_m = $_POST['apellido_materno'];
    $password = $_POST['password'];
    $puesto = $_POST['puesto'];
    $sucursal_idSucursal = 1;

    $sql = "Update Empleado set nombre = '".$nombre."', apellido_paterno = '".$apellido_p."',
        apellido_materno = '".$apellido_m."', password = '".$password."', puesto = '".$puesto."'
        where clave_empleado = '".$clave_empleado."';";

    echo $sql;

    mysqli_query($conexion, $sql) or die(mysqli_error());
    mysqli_close($conexion);
?>