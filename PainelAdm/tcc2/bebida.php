<?php
require 'db_credentials.php';
require 'authenticate.php';
require 'lib/sanitize.php';

if(!$login){
  header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/login.php");}

 $conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket);

if (!$conn) {
  die("Problemas ao conectar com o BD!<br>".
       mysqli_connect_error());
}
if(isset($_GET["msg"])){
    echo "<script>alert('".$_GET['msg']."');</script>";
}

if ($_SERVER["REQUEST_METHOD"] == "GET"){  

      if(isset($_GET["cadastrar"]) == "cadastrar"){

        $mysqlImg="";
        $imagem = $_FILES["image"];

        $marcaBebida = sanitize($_GET["marcaBebida"]);
        $marcaBebida = mysqli_real_escape_string($conn,$marcaBebida);

        $preçoBebida = sanitize(($_GET["preçoBebida"]));
        $preçoBebida = mysqli_real_escape_string($conn,$preçoBebida);
        
        $quantidadeBebida = sanitize(($_GET["quantidadeBebida"]));
        $quantidadeBebida = mysqli_real_escape_string($conn,$quantidadeBebida);

        if($imagem != null){
          $nomeFinal = time().'.jpg';
          if(move_uploaded_file($imagem['tmp_name'], $nomeFinal)) { //valida se o arquivo é um arquivo de upload valido
            $tamanhoImg = filesize($nomeFinal); //pega o tamnho do arquivo
            $mysqlImg = addslashes(fread(fopen($nomeFinal, "r"), $tamanhoImg)); // abre o arquivo, le o arquivo e prepara-o
            echo "imagem preparada com sucesso";     
          }  
        }


        if(empty($marcaBebida) and empty($preçoBebida) and empty($quantidadeBebida) )  {
          echo '<script>alert("Favor preencher todos os campos!"); </script>'; 
        }elseif(empty($marcaBebida)  or empty($preçoBebida) or empty($quantidadeBebida) ) {
          echo '<script>alert("Favor preencher todos os campos!"); </script>'; 
        }elseif(!empty($marcaBebida) and !empty($preçoBebida) and !empty($quantidadeBebida)) {


          $sql = "INSERT into bebida(`marca`,`preco`,`quantidade`) values ('$marcaBebida', '$preçoBebida', '$quantidadeBebida')";
          unlink($imagem);
          if(!mysqli_query($conn,$sql)){
            die("Problemas para cadastrar Bebida!<br>".
                 mysqli_error($conn));
          }else{
            echo '<script>alert("Bebida cadastrada com sucesso!"); </script>';           
          }  
        }
      
        
    }elseif(isset($_GET["pesquisar"]) == "pesquisar"){
       
      $bt = $_GET["pesquisar"];

        $marcaBebida = sanitize($_GET["marcaBebida"]);
        $marcaBebida = mysqli_real_escape_string($conn,$marcaBebida);

        $preçoBebida = sanitize(($_GET["preçoBebida"]));
        $preçoBebida = mysqli_real_escape_string($conn,$preçoBebida);
        
        $quantidadeBebida = sanitize(($_GET["quantidadeBebida"]));
        $quantidadeBebida = mysqli_real_escape_string($conn,$quantidadeBebida);

      if(empty($marcaBebida) and empty($preçoBebida) and empty($quantidadeBebida)) {
        $sql = "SELECT * from bebida";
          if(!($bebidas = mysqli_query($conn,$sql))){
            die("Problemas para carregar as bebidas do BD!<br>".
                 mysqli_error($conn));}
        }else{
          $sql = "SELECT * from bebida WHERE marca = '$marcaBebida' or preco = '$preçoBebida' or quantidade ='$quantidadeBebida' ";
          if(!($bebidas = mysqli_query($conn,$sql))){
            die("Problemas para carregar as bebidas do BD!<br>".
                 mysqli_error($conn));
          }
        }

    }elseif (isset($_GET["excluir"]) == "excluir") {
      
      $num = $_GET["excluir"];
        $sql = "DELETE from bebida WHERE id_prodDiversos='$num'";
          if(mysqli_query($conn,$sql)){
            echo '<script>alert("Bebida excluída com sucesso!"); </script>';   
          }else{
            echo '<script>alert("Erro ao excluir bebida!"); </script>'; 
          }

    }


}

?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Hookah Bar</title>

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Customização CSS -->
    <link rel="stylesheet" href="styles.css">
    <!-- javascripts -->
    

    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
</head>

<body>
  <div class="container-wrapper">    
    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

        <a class="navbar-brand mr-1" href="pedidos.php">Hookah Bar</a>
        <!-- Navbar Search -->
        <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Procurar..." aria-label="Procurar" aria-describedby="basic-addon2">
            <div class="input-group-append">
              <button class="btn btn-primary" type="button">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </div>
        </form>

        <!-- Navbar -->
        <ul class="navbar-nav ml-auto ml-md-0">
          <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
              <a class="dropdown-item" href="#">Usuários</a>
              AW2 
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="logout.php">Logout</a>
            </div>
          </li>
        </ul>
    </nav> <!-- FiM DO NAVBAR SUPERIOR -->


    <div id="wrapper">
      <div class="row">
      <div class="col-md-3" id="navloca">
         <nav id="sidebar">
            <!-- <div class="sidebar-header">
                <h3>Hookah Bar</h3>
            </div> -->

           <ul class="sidebar navbar-nav">
                <li>
                    <a href="pedidos.php">Pedidos</a>
                </li>
                <li class="active">
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown">Produtos</a>
                    <ul class="collapse list-unstyled" id="homeSubmenu">
                        <li>
                            <a href="carvao.php">Carvão</a>
                        </li>
                        <li>
                            <a href="aluminio.php">Alumínio</a>
                        </li>
                        <li>
                            <a href="Essencia.php">Essência</a>
                        </li>
                        <li>
                            <a href="rosh.php">Rosh</a>
                        </li>
                        <li>
                            <a href="steam.php">Steam</a>
                        </li>
                        <li>
                            <a href="mangueira.php">Mangueira</a>
                        </li>
                        <li>
                            <a href="vaso.php">Vaso</a>
                        </li>
                        <li>
                            <a href="bebida.php">Bebidas</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="mesas.php">Mesas</a>
                </li>
                <li>
                    <a href="funcionario.php">Funcionário</a>
                </li>
                <li>
                    <a href="relatorio.php">Relatório</a>
                </li>
            </ul>
        </nav>
      </div>    
        <!-- Início conteudo -->
      <div class="col-md-9" id="padding">  
        <div id="content-wrapper">      
          <div class="row">
            <div class="container-fluid">  
              <ol class="breadcrumb">
                  <li class="breadcrumb-item">
                    <a href="pedidos.php">Dashboard Pedido</a>
                  </li>
                  <li class="breadcrumb-item active">Produtos - Bebidas </li>
              </ol>
            </div>
          </div>   
          <div class="card">
                  <div class="card-header">
                    <!-- <i class="fas fa-table"></i> -->
                    <h3>Bebidas</h3>
                  </div>
               
                  <div class="card-body">
                      <form  method="GET" action="<?php echo $_SERVER['PHP_SELF']; ?>" enctype="multipart/form-data"> 
                        <div class="row">
                          <div  class="container-fluid">
                            <!-- <div class="card mb-3"> -->                    
                             <!-- <div class="card-header">
                                <h3>Pesquisar</h3>
                              </div> -->
                            <div class="row">
                              <div class="col-md-6">                  
      
                                <label for="exampleFormControlInput1">Marca</label>
                                <input type="text" class="form-control" id="marcaBebida" name="marcaBebida" placeholder="Marca">
                                <label for="exampleFormControlInput1">Preço</label>
                                <input type="number" step="0.01" min="0" class="form-control" id="preçoBebida" name="preçoBebida" placeholder="Preço">
                              </div>  
                              <div class="col-md-6 ">  
                                <label for="exampleFormControlInput1">Quantidade</label>
                                <input type="number" class="form-control" step="0.5" min="0" id="quandidadeBebida" name="quantidadeBebida" placeholder="Quantidade">
                                <label for="exampleFormControlInput1">Escolha um arquivo de imagem</label>
                                <input type="file" accept="image/png, image/jpeg" class="form-control" id="image" name="image">  
                                <br>  
                                <button class="btn btn-primary" type="submit" id="cadastrar" class="floated" name="cadastrar" value="cadastrar">Cadastrar</button>
                                <button class="btn btn-primary" type="submit" id="pesquisar" class="floated" name="pesquisar" value="pesquisar">Pesquisar</button>  
                              </div>
                            </div>               
                          </div>
                  
                          <!-- DIV CONSULTA ESCONDIDO -->
                          <br>
                            <div class="table-responsive" id="conteudo">
                              <table class="table table-bordered" id="tableResultado" width="100%" cellspacing="0">
                                <thead>
                                  <tr>
                                    <th>Marca</th>
                                    <th>Preço</th>
                                    <th>Quantidade</th>
                                    <th>Ação</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <?php if(isset($bebidas)){ ?>
                                    <?php if(mysqli_num_rows($bebidas) > 0): ?>
                                      <?php while($uni = mysqli_fetch_assoc($bebidas)): ?> 
                                        <?php echo '<tr><td>' . $uni["marca"] . '</td><td>' . 'R$ ' . $uni["preco"] . '</td><td>' . $uni["quantidade"] .'</td><td>' . '<a class="btn btn-primary" href=bebidaEdit.php?id='.$uni["id_prodDiversos"]. '>Editar</a>' .  ' ' . '<button class="btn btn-primary" type="submit" id="excluir" class="floated" name="excluir" value='.$uni["id_prodDiversos"].'>Excluir</button>' . '</td></tr>' ?>

                                      <?php endwhile; ?>
                                    <?php else: ?>
                                      Nenhuma busca realizada!
                                    <?php endif; ?>
                                  <?php } ?>  
                                </tbody>
                              </table>
                            </div>
                          <!-- FIM DIV ESCONDIDA -->
                          

                        </div> <!-- div row form -->
                      </form>
                    </div> <!-- Card body -->
                  </div>  <!-- card -->
            </div>  <!--content-wrapper -->
          </div>   <!-- col-->

        </div> <!--row-->  
      </div> <!-- ID WRAPPER-->
    </div>    <!-- container-wrapper -->

    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>

</html>