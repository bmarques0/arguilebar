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
<html class="gr__getbootstrap_com">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="icon" href="../../../../favicon.ico">
    </head>
    <body class="text-center">  
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">
                                <?php if ($login): ?>
                                    <h3>Você já está logado!</h3>
                                    <a class="btn btn-primary" href=pedidos.php>Tela Inicial</a>
                                  <?php exit(); ?>
                                <?php endif; ?>

                                <?php if ($error): ?>
                                    <h3 style="color:red;"><?php echo $error_msg; ?></h3>
                                <?php endif; ?>
                            </h5>
                            <form class="form-signin" action="LoginServlet" method="post">
                                <img class="mb-4" src="https://seeklogo.com/images/P/psicologia-logo-1E81DC14D9-seeklogo.com.png" alt="" width="72" height="72">
                                <label type="text" class="sr-only">Endereço de email</label>
                                <input type="email" id="inputEmail" class="form-control" placeholder="Login" name="login" required autofocus>
                                <label for="inputPassword" class="sr-only">Senha</label>
                                <input type="password" id="inputPassword" class="form-control" placeholder="Senha" name="password"required>
                                <div class="checkbox mb-3">
                                    <label>
                                        <input type="checkbox" value="remember-me"> Lembrar de mim
                                    </label>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                                <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
                            </form>
                            <h3 style="color: red">${mensagem}</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>