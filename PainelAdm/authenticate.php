<?php
  session_start();

  if (isset($_SESSION["login"]) && isset($_SESSION["senha"])) {
    $login = true;
    $loginusu = $_SESSION["login"];
    $senhausu = $_SESSION["senha"];
  }
  else{
    $login = false;
  }

?>
