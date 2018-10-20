<?php
require 'db_credentials.php';
require "authenticate.php";


if(!$login){
  header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/login.php");}

$conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket);

if (!$conn) {
  die("Problemas ao conectar com o BD!<br>".
       mysqli_connect_error());
}

if ($_SERVER["REQUEST_METHOD"] == "GET"){


  if(isset($_GET["cadastrar"])){
    $categoriaEssencia = ($_GET["categoriaEssencia"]);
    $marcaEssencia = ($_GET["marcaEssencia"]);
    $preçoEssencia = ($_GET["preçoEssencia"]);
    $saborEssencia = ($_GET["saborEssencia"]);  
    $sql = "INSERT into essencia(`categoria`,`marca`,`preco`,`sabor`) values ('$categoriaEssencia', '$marcaEssencia', '$preçoEssencia', '$saborEssencia')";
    if(!mysqli_query($conn,$sql)){
      die("Problemas para cadastrar Essencia!<br>".
           mysqli_error($conn));
    }else{
      echo "Cadastrado com sucesso";
    }
  }elseif(isset($_GET["pesquisar"])){
    $categoriaEssencia = ($_GET["categoriaEssencia"]);
    $marcaEssencia = ($_GET["marcaEssencia"]);
    $preçoEssencia = ($_GET["preçoEssencia"]);
    $saborEssencia = ($_GET["saborEssencia"]);
    
    if(isset($marcaEssencia)){
      $sql = "SELECT * FROM essencia WHERE marca='$marcaEssencia'";
      if(!($essencias = mysqli_query($conn,$sql))){
        die("Problemas para carregar as Essencias do BD!<br>".
             mysqli_error($conn));
      }
    }elseif(isset($preçoEssencia)) {
      $sql = "SELECT * FROM essencia WHERE preco='$preçoEssencia'";
      if(!($essencias = mysqli_query($conn,$sql))){
        die("Problemas para carregar os Essencias do BD!<br>".
             mysqli_error($conn));
      }  
    }elseif (isset($saborEssencia)) {
      $sql = "SELECT * FROM essencia WHERE sabor='$saborEssencia'";
      if(!($essencias = mysqli_query($conn,$sql))){
        die("Problemas para carregar os Essencias do BD!<br>".
             mysqli_error($conn));
      }
    }elseif (isset($categoriaEssencia)) {
      $sql = "SELECT * FROM essencia WHERE categoria='$categoriaEssencia'";
      if(!($essencias = mysqli_query($conn,$sql))){
        die("Problemas para carregar os Essencias do BD!<br>".
             mysqli_error($conn));
      }  
    }  
    
  }
}

$sql = "SELECT * FROM essencia ";
if(!($essencias = mysqli_query($conn,$sql))){
  die("Problemas para carregar os cursos do BD!<br>".
       mysqli_error($conn));
}

?>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Front TCC </title>
    <!-- css DIV escondida -->
    <link href="styles.css" rel="stylesheet">

    <!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">
    <!-- <style type="text/css">
        #conteudo{
          display: none;

        }
        .table-resposive{
          display: none;
 
        }
    </style> -->

  </head>

  <body>
    <div class="container-wrapper">
      <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

        <a class="navbar-brand mr-1" href="index.php">Hookah Bar</a>
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
              
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="logout.php">Logout</a>
            </div>
          </li>
        </ul>

      </nav>

      <div id="wrapper">
        <!------------------------------------MENU LATERAL------------>
        
          <!-- Sidebar -->
          <ul class="sidebar navbar-nav">
            <li class="nav-item active">
              <div class="card text-white bg-primary o-hidden h-100">
                    <div class="card-body">
                      <div class="card-body-icon">
                        <i class="fas fa-fw fa-comments"></i>
                      </div>
                      <div class="mr-5">Pedidos</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                      <span class="float-left">Gerenciar Pedidos</span>
                      <span class="float-right">
                        <i class="fas fa-angle-right"></i>
                      </span>
                    </a>
              </div>
              
            </li>
            <li class="nav-item">
              <div class="card text-white bg-warning o-hidden h-100">
                    <div class="card-body">
                      <div class="card-body-icon">
                        <i class="fas fa-fw fa-list"></i>
                      </div>
                      <div class="mr-5">Arguiles</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                      <span class="float-left">Gerenciar Arguiles</span>
                      <span class="float-right">
                        <i class="fas fa-angle-right"></i>
                      </span>
                    </a>
              </div>   
            
            </li>
            <li class="nav-item">
              <div class="card text-white bg-success o-hidden h-100">
                    <div class="card-body">
                      <div class="card-body-icon">
                        <i class="fas fa-fw fa-shopping-cart"></i>
                      </div>
                      <div class="mr-5">Produtos</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                      <span class="float-left">Gerenciar Produtos</span>
                      <span class="float-right">
                        <i class="fas fa-angle-right"></i>
                      </span>
                    </a>
              </div>
            </li>
            <li class="nav-item">
              <div class="card text-white bg-danger o-hidden h-100">
                    <div class="card-body">
                      <div class="card-body-icon">
                        <i class="fas fa-fw fa-life-ring"></i>
                      </div>
                      <div class="mr-5">Funcionarios</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="#">
                      <span class="float-left">Gerenciar Funcionarios</span>
                      <span class="float-right">
                        <i class="fas fa-angle-right"></i>
                      </span>
                    </a>
                  </div>
            </li>
          </ul>
            
        <!------------------------------------------------------------>

        
                <div id="content-wrapper">
                  <div class="row">  
                    <div class="container-fluid">
                      <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                          <a href="produtoEssencia.php"><h2>Essências</h2></a>
                        </li>
                      </ol>
                    </div> <!-- */container-fluid -->    
                  </div>

                  <div class="card">
                    <div class="card-body">
                      <form  method="GET" action="produtoEssencia.php" > 
                        <div class="row">
                          <div  class="container-fluid">
                            <!-- <div class="card mb-3"> -->                    
                              <div class="card-header">
                                <h3>Pesquisar</h3>
                              </div>
                            <div class="row">
                              <div class="col-md-6">                  
                                <label for="exampleFormControlSelect1">Categoria</label>
                                <select class="form-control" id="categoriaEssencia" name="categoriaEssencia">
                                    <option>Premium</option>
                                    <option>Normal</option>   
                                </select>
                                <label for="exampleFormControlInput1">Marca</label>
                                <input type="text" class="form-control" id="marcaEssencia" name="marcaEssencia" placeholder="Marca">  
                              </div>  
                              <div class="col-md-6 ">  
                                <label for="exampleFormControlInput1">Preço</label>
                                <input type="number" class="form-control" id="preçoEssencia" name="preçoEssencia" placeholder="Preço">
                                <label for="exampleFormControlInput1">Sabor</label>
                                <input type="text" class="form-control" id="saborEssencia" name="saborEssencia" placeholder="Sabor">
                                <br>  
                                <button class="btn btn-primary" id="cadastrar" name="cadastrar">Cadastrar</button>
                                <button class="btn btn-primary" id="pesquisar" name="pesquisar">Pesquisar</button>  
                              </div>
                            </div>               
                          </div>

                          <!-- DIV CONSULTA ESCONDIDO ------>
                          <div class="table-responsive" id="conteudo">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                              <thead>
                                <tr>
                                  <th>ID Essência</th>
                                  <th>Categoria</th>
                                  <th>Sabor</th>
                                  <th>Marca</th>
                                  <th>Preço</th>                                  
                                </tr>
                              </thead>
                              <tbody>
                                <?php if(mysqli_num_rows($essencias) > 0): ?>
                                  <?php while($uni = mysqli_fetch_assoc($essencias)): ?>  
                                    <?php echo '<tr><td>'. $uni["id_essencia"] . '</td><td>' . $uni["categoria"] . '</td><td>' . $uni["sabor"] . '</td><td>' . $uni["marca"] . '</td><td>' . $uni["preco"] . '</td></tr>' ?>
                                  <?php endwhile; ?>
                                <?php else: ?>
                                  Nenhuma essência cadastrada!!!
                                <?php endif; ?>
                          
                              </tbody>
                            </table>
                          </div>
                          <!-- FIM DIV ESCONDIDA -->

                          <script type="text/javascript">
                            $(document).ready(function(){
                              $("#pesquisar").onclick(function() {
                                $("#conteudo").css("display")="none"); 
                              };
                            });
                          </script>

                        </div>
                      </form>
                    </div>
                  </div>
                </div> <!-- /.container-fluid -->
 
        </div>
        <!-- /.content-wrapper -->

      </div> <!-- /#wrapper -->
    </div>    

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="js/demo/datatables-demo.js"></script>
    <script src="js/demo/chart-area-demo.js"></script>


  </body>

</html>