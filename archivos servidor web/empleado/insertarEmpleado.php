<?php
    include '../conexion.php';

    $clave_empleado = $_POST['clave_empleado'];
    $nombre = $_POST['nombre'];
    $apellido_p = $_POST['apellido_paterno'];
    $apellido_m = $_POST['apellido_materno'];
    $password = $_POST['password'];
    $puesto = $_POST['puesto'];
    $sucursal_idSucursal = 1;

    $sql = "Insert INTO Empleado (clave_empleado, nombre, apellido_paterno, apellido_materno, password,
        puesto, Sucursal_idSucursal) values
        ('".$clave_empleado."', '".$nombre."', '".$apellido_p.
        "', '".$apellido_m."', '".$password."', '".$puesto."', '".$sucursal_idSucursal."');";

    echo $sql;

    mysqli_query($conexion, $sql) or die (mysqli_error());
    mysqli_close();
?>