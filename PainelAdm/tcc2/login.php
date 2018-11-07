<?php
require "db_credentials.php";
require "authenticate.php";

$error = false;
$senha = $usuario = "";

if (!$login && $_SERVER["REQUEST_METHOD"] == "POST") {                              //verifica se foi acionado o método post do login
  if (isset($_POST["usuario"]) && isset($_POST["senha"])) {                         //Verifica se variáveis contem informações || se foi iniciada

    $conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket);         //passa dados do servidor mysql
    if (!$conn) {                                                                                       
        die("Problemas ao conectar com o BD!<br>".              
            mysqli_connect_error());
    }else{
        echo "Conectado ao BD!";
    }

    $usuario = mysqli_real_escape_string($conn,$_POST["usuario"]);                  //prepara as variaveis para utilizar em um código SQL
    $senha = mysqli_real_escape_string($conn,$_POST["senha"]);
    //$senha = md5($senha);                                                         

    $sql = "SELECT * FROM usuario WHERE login='$usuario';";

    $result = mysqli_query($conn, $sql);      //executa a query no banco de dados
    if($result){                                //se houver dados na query
      if (mysqli_num_rows($result) > 0) {       //se o numero de linhas da consulta for maior que 0     
        $user = mysqli_fetch_assoc($result);        //retorna o resultado da linha na variavel cuja verificação foi feita no WHERE 

        if ($user["senha"] == $senha) {

              $_SESSION["login"] = $user["login"];     //pega o parametro buscado na query e insere na sessão[login]
              $_SESSION["senha"] = $user["senha"];

              header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/pedidos.php");
              exit();

              /**if ($_SESSION["login"] == "" ){
                header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/index.php");
                exit();
              }else{
                header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/login.php");
                exit();
              }*/  
        }
        else {
          $error_msg = "Senha incorreta!";
          $error = true;
        }
      }
      else{
        $error_msg = "Usuário não encontrado!";           // verificação mysqli_num_rows
        $error = true;
      }
    }
    else {  
      $error_msg = mysqli_error($conn);                                
      $error = true;
    }
  }
  else {
    $error_msg = "Por favor, preencha todos os dados.";   //variável nao foi iniciada
    $error = true;
  }
}
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <title>Login - Sistema IAC</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="stylelogin.css">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container bootstrap snippet">
    <div class="lc-block col-md-4 col-md-offset-4 toggled" id="l-login">
        <div class="lcb-float"><i class="fa fa-users"></i></div>
        <?php if ($login): ?>
            <h3>Você já está logado!</h3>
          <?php exit(); ?>
        <?php endif; ?>

        <?php if ($error): ?>
            <h3 style="color:red;"><?php echo $error_msg; ?></h3>
        <?php endif; ?>        

        <form method="post" action="login.php">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Usuário" name="usuario" value="" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Senha" name="senha" value="" required>
            </div>
            <div class="clearfix"></div>
            <input type="submit" name="submit" value="Entrar"  class="btn btn-block btn-primary btn-float m-t-25">
        </form>
    </div>
</div>         
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript"></script>
</body>
</html>